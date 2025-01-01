package com.pawstime.pawstime.domain.post.facade;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.image.entity.Image;
import com.pawstime.pawstime.domain.image.entity.repository.ImageRepository;
import com.pawstime.pawstime.domain.post.dto.req.CreatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.req.UpdatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetDetailPostRespDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.domain.post.service.CreatePostService;
import com.pawstime.pawstime.domain.post.service.GetDetailPostService;
import com.pawstime.pawstime.domain.post.service.GetListPostService;
import com.pawstime.pawstime.domain.post.service.ReadPostService;
import com.pawstime.pawstime.domain.post.service.S3Service;
import com.pawstime.pawstime.domain.post.service.UpdatePostService;
import com.pawstime.pawstime.global.exception.InvalidException;
import com.pawstime.pawstime.global.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class PostFacade {

    private final ReadPostService readPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final GetDetailPostService getDetailPostService;
    private final GetListPostService getListPostService;
    private final PostRepository postRepository;
    private final S3Service s3Service;
    private final ImageRepository imageRepository;

    public void createPost(CreatePostReqDto req, List<String> imageUrls) {
        // 입력값 검증
        validateCreatePostRequest(req);

        // 게시판 검증
        Board board = validateBoard(req.boardId());

        // 게시글 생성
        Post post = createPostEntity(req, board);

        // 이미지 추가
        if (imageUrls != null && !imageUrls.isEmpty()) {
            addImagesToPost(post.getPostId(), imageUrls);
        }

        // 게시글 저장
        createPostService.createPost(post);
    }

    private void validateCreatePostRequest(CreatePostReqDto req) {
        if (req.boardId() == null || req.title() == null || req.content() == null) {
            throw new InvalidException("필수 입력값이 누락되었습니다.");
        }
    }

    private Board validateBoard(Long boardId) {
        Board board = readPostService.findBoardById(boardId);
        if (board == null || board.isDelete()) {
            throw new NotFoundException("유효하지 않은 게시판입니다.");
        }
        return board;
    }

    private Post createPostEntity(CreatePostReqDto req, Board board) {
        return req.toEntity(board);
    }

    public void addImagesToPost(Long postId, List<String> imageUrls) {
        // 게시글 조회
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if (post.isDelete()) {
            throw new InvalidException("삭제된 게시글에는 이미지를 추가할 수 없습니다.");
        }

        if (post.getImages() == null) {
            post.setImages(new ArrayList<>());
        }

        // 이미지 URL을 기준으로 Image 엔티티 생성
        for (String imageUrl : imageUrls) {
            Image img = new Image();
            img.setImageUrl(imageUrl);
            img.setPost(post); // 해당 이미지가 게시글에 속하도록 설정
            post.addImage(img); // 게시글에 이미지 추가
        }

        // 이미지 추가 후 게시글 저장
        postRepository.save(post);
        }

    // 게시글 수정
    public void updatePost(Long postId, UpdatePostReqDto req) {
        // 게시글 조회
        Post post = readPostService.findPostById(postId);

        // 게시글 존재 여부 및 삭제 상태 확인
        if (post == null) {
            throw new NotFoundException("존재하지 않는 게시글 ID입니다.");
        }
        if (post.isDelete()) {
            throw new NotFoundException("이미 삭제된 게시글입니다.");
        }

        // 게시글 수정
        updatePostService.updatePost(post, req);
    }


    //게시글 삭제
    public void deletePost(Long postId) {
        // 게시글 조회
        Post post = readPostService.findPostById(postId);

        if (post == null) {
            throw new NotFoundException("존재하지 않는 게시글 ID입니다.");
        }
        if (post.isDelete()) {
            throw new NotFoundException("이미 삭제된 게시글입니다.");
        }

        // 게시글에 연결된 이미지들
        List<Image> images = post.getImages();

        // 이미지 URL을 추출하여 S3에서 삭제하고, DB에서도 삭제
        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = images.stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());

            // 각 이미지 URL을 S3에서 삭제
            for (String imageUrl : imageUrls) {
                String fileName = extractFileNameFromUrl(imageUrl); // URL에서 파일 이름 추출
                s3Service.deleteFile(fileName); // S3에서 파일 삭제

                // DB에서 이미지 엔티티 삭제
                imageRepository.deleteByImageUrl(imageUrl); // 이미지 URL로 삭제
            }
        }

        // 게시글 소프트 삭제 처리
        post.softDelete();

        // 소프트 삭제된 게시글 상태 저장
        createPostService.createPost(post);
    }

    public GetDetailPostRespDto getDetailPost(Long postId) {
        // 게시글 조회
        Post post = readPostService.findPostId(postId);

        // 게시글이 존재하지 않거나 삭제된 상태인지 체크
        if (post == null || post.isDelete()) {
            throw new NotFoundException("존재하지 않거나 삭제된 게시글입니다.");
        }

        return getDetailPostService.getDetailPost(postId);  // DTO 반환
    }

    // 게시글 목록 조회 (정렬 기준 및 방향 추가)
    public Page<GetListPostRespDto> getPostList(Long boardId, String keyword, Pageable pageable, String sortBy, String direction) {
        // getListPostService에서 정렬 기준을 포함하여 서비스 호출
        return getListPostService.getPostList(boardId, keyword, pageable, sortBy, direction);
    }

    // 게시글 ID로 조회
    public Post getPostId(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));
    }

    // URL에서 파일 이름을 추출하는 유틸리티 메서드
    private String extractFileNameFromUrl(String imageUrl) {
        // URL에서 파일 이름 추출 로직
        // 예: https://s3.amazonaws.com/bucket_name/filename.jpg -> filename.jpg 추출
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }
}