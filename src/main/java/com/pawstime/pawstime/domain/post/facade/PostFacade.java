package com.pawstime.pawstime.domain.post.facade;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.image.dto.resp.GetImageRespDto;
import com.pawstime.pawstime.domain.image.entity.Image;
import com.pawstime.pawstime.domain.image.entity.repository.ImageRepository;
import com.pawstime.pawstime.domain.image.service.ReadImageService;
import com.pawstime.pawstime.domain.image.service.UpdateImageService;
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
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


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
    private final UpdateImageService updateImageService;
    private final ReadImageService readImageService;



    //게시글 생성

    public Long createPost(CreatePostReqDto req) {
        // 입력값 검증
        validateCreatePostRequest(req);

        // 게시판 검증
        Board board = validateBoard(req.boardId());

        // 게시글 생성
        Post post = createPostEntity(req, board);

        // 게시글 저장
        Post savedPost = createPostService.createPost(post);

        return savedPost.getPostId();
    }

    private void validateCreatePostRequest(CreatePostReqDto req) {
        if (req == null || req.boardId() == null || req.title() == null || req.content() == null) {
            throw new InvalidException("필수 입력값이 누락되었습니다.");
        }
    }

    private Board validateBoard(Long boardId) {
        // 게시판 조회
        Board board = readPostService.findBoardById(boardId);

        // 게시판이 존재하지 않거나 삭제된 게시판인 경우 예외 처리
        if (board == null || board.isDelete()) {
            throw new NotFoundException("유효하지 않은 게시판입니다.");
        }

        return board;
    }

    private Post createPostEntity(CreatePostReqDto req, Board board) {
        return req.toEntity(board);  // req를 통해 Post 엔티티 생성
    }

    /// //////////////////////////////////////////
    @Value("${default.img-url}")
    private String defaultImageUrl;

    @Transactional
    public void addImagesToPost(Long postId, List<String> imageUrls) {

        // 포스트 엔티티 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시글을 추가할 수 없습니다."));

        // 삭제된 게시글에는 이미지를 추가할 수 없도록 처리
        if (post.isDelete()) {
            throw new NotFoundException("삭제된 게시글에는 이미지를 추가할 수 없습니다.");
        }

        // 이미지 리스트가 비어있거나 null인 경우
        if (imageUrls == null || imageUrls.isEmpty()) {
            // 이미지가 없으면 기본 이미지 추가
            if (post.getImages().isEmpty()) {
                try {
                    // 기본 이미지 업로드
                    String uploadedImageUrl = s3Service.uploadDefaultImageToS3("static/default-img.jpg"); // 기본 이미지 업로드
                    Image defaultImage = new Image();
                    defaultImage.setImageUrl(uploadedImageUrl); // 업로드된 이미지 URL 사용
                    defaultImage.setPost(post);
                    post.addImage(defaultImage);
                } catch (Exception e) {
                    // 기본 이미지 업로드 실패 시 로그 기록
                    log.error("기본 이미지 업로드 실패ss: {}", e.getMessage());
                    throw new RuntimeException("이미지 업로드 실패sss: " + e.getMessage());
                }
            }
        }  else {
            // 이미지 URL 리스트가 비어 있지 않으면 사용자가 추가한 이미지 처리
            for (String imageUrl : imageUrls) {
                Image image = Image.builder()
                        .imageUrl(imageUrl)
                        .isDefault(false)
                        .build();
                post.addImage(image); // 포스트에 이미지 추가 (이미 setPost() 메서드가 호출됨)
            }
        }

        postRepository.save(post); // 포스트 저장

    }


    // 게시글 수정
    public void updatePost(Long postId, UpdatePostReqDto req) {
        // 게시글 조회
        Post post = readPostService.findPostById(postId);

        // ******** 게시글 작성자 확인 후 게시글 수정을 요청한 유저가 해당 게시글을 작성한 유저와 일치하는지
        // 아니면 관리자 권한을 가지고 있는지 확인하는 로직 추가 필요 ********

        // 게시글 존재 여부 및 삭제 상태 확인
        if (post == null) {
            throw new NotFoundException("존재하지 않는 게시글 ID입니다.");
        }
        if (post.isDelete()) {
            throw new NotFoundException("이미 삭제된 게시글입니다.");
        }
        updatePostService.updatePost(post, req);

        postRepository.save(post); // 게시글 저장
    }

    @Transactional
    public void updatePostImages(Long postId, List<Long> deletedImageIds, List<MultipartFile> newImages) {
        // 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시글이 존재하지 않습니다."));

        // 삭제할 이미지 처리
        if (deletedImageIds != null && !deletedImageIds.isEmpty()) {
            List<Long> deletedImageIdsList = updateImageService.deleteImagesFromPost(deletedImageIds, post);

            // 삭제된 이미지 리스트 반환 (필요에 따라 로깅하거나 처리 가능)
            log.info("삭제된 이미지 ID 목록: {}", deletedImageIdsList);
        }

        // 새로 추가할 이미지 처리
        if (newImages != null && !newImages.isEmpty()) {
            List<Map<String, Object>> newImagesInfo = updateImageService.addNewImagesToPost(newImages, post);

        }
    }


    public void deletePost(Long postId) {
        // 게시글 조회
        Post post = readPostService.findPostById(postId);

        if (post == null) {
            throw new NotFoundException("존재하지 않는 게시글 ID입니다.");
        }
        if (post.isDelete()) {
            throw new NotFoundException("이미 삭제된 게시글입니다.");
        }

        // 게시글에 연관된 이미지들 조회
        List<Image> images = post.getImages();

        // 이미지 URL 추출하여 삭제
        if (images != null && !images.isEmpty()) {
            List<String> imageUrls = images.stream()
                    .map(Image::getImageUrl) // 이미지 URL 추출
                    .collect(Collectors.toList());

            // 각 이미지 URL을 기반으로 파일 이름을 추출하고 S3에서 삭제
            for (String imageUrl : imageUrls) {
                String fileName = extractFileNameFromUrl(imageUrl); // URL에서 파일 이름 추출
                s3Service.deleteFile(fileName); // S3에서 파일 삭제
            }

            // 이미지 목록을 비워서 orphan removal이 자동으로 처리하도록 합니다.
            post.getImages().clear(); // 이미지를 삭제한 후, 게시글에서 이미지 목록을 비웁니다.
        }

        // 소프트 삭제 처리
        post.softDelete();

        // 게시글 상태 저장
        postRepository.save(post);  // 게시글만 저장
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

  public Page<GetImageRespDto> getThumbnail(Long postId) {
      Post post = readPostService.findPostId(postId);

      if (post == null || post.isDelete()) {
          throw new NotFoundException("존재하지 않거나 삭제된 게시글입니다.");
      }

      Pageable pageable = PageRequest.of(0, 1);
      // 게시글에 있는 이미지 중 제일 첫번째 이미지만 가져오도록 Pageable사용

      Page<GetImageRespDto> thumbnail = readImageService.getThumbnail(postId, pageable).map(GetImageRespDto::from);

      if (thumbnail.isEmpty()) {
          GetImageRespDto defaultImage = new GetImageRespDto(null, null, postId);
          return new PageImpl<>(List.of(defaultImage), pageable, 1);
      }

      return thumbnail;
  }

    public List<GetImageRespDto> getImages(Long postId) {
        Post post = readPostService.findPostId(postId);

        if (post == null || post.isDelete()) {
            throw new NotFoundException("존재하지 않거나 삭제된 게시글입니다.");
        }

        List<Image> images = readImageService.getImages(postId);

        if (images.isEmpty()) {
            GetImageRespDto defaultImage = new GetImageRespDto(null, null, postId);
            return List.of(defaultImage);
        }

        return images.stream().map(GetImageRespDto::from).toList();
    }

    public Page<GetImageRespDto> getRandomImages() {
        Pageable pageable = PageRequest.of(0, 5);
        // 전체 이미지 중 5개의 이미지만 가져오도록 Pageable사용 (repository에서 랜덤으로 정렬한 후 5개만 가져옴)

        return readImageService.getRandomImages(pageable).map(GetImageRespDto::from);
    }
}