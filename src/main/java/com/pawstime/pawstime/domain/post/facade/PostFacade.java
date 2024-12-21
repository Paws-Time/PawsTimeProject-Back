package com.pawstime.pawstime.domain.post.facade;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.service.ReadBoardService;
import com.pawstime.pawstime.domain.post.dto.req.CreatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.req.UpdatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetDetailPostRespDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.domain.post.service.*;
import com.pawstime.pawstime.global.exception.DuplicateException;
import com.pawstime.pawstime.global.exception.InvalidException;
import com.pawstime.pawstime.global.exception.NotFoundException;
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


    public void createPost(CreatePostReqDto req) {
        if (req.boardId() == null || req.boardId().toString().isEmpty()) {
            throw new InvalidException("게시판 ID는 필수 입력값입니다.");
        }

        Board board = readPostService.findBoardById(req.boardId());
        if (board == null) {
            throw new InvalidException("해당 Board ID는 존재하지 않습니다.");
        }

        if (req.title() == null || req.title().isEmpty()) {
            throw new InvalidException("게시글 제목은 필수 입력값입니다.");
        }
        if (req.content() == null || req.content().isEmpty()) {
            throw new InvalidException("게시글 내용은 필수 입력값입니다.");
        }

        // 게시글 생성 로직
        Post post = req.toEntity(board);
        createPostService.createPost(post);
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

        // 예외 처리
        if (post == null) {
            throw new NotFoundException("존재하지 않는 게시글 ID입니다.");
        }
        if (post.isDelete()) {
            throw new NotFoundException("이미 삭제된 게시글입니다.");
        }

        // 소프트 삭제 처리
        post.softDelete();

        // 상태 저장
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



}