package com.pawstime.pawstime.domain.post.facade;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.post.dto.req.CreatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.req.UpdatePostReqDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetDetailPostRespDto;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.domain.post.service.*;
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
  private final DeletePostService deletePostService;
  private final GetDetailPostService getDetailPostService;
  private final PostRepository postRepository;

  public void createPost(CreatePostReqDto req) {
    Board board = readPostService.findBoardById(req.boardId());
    if (board == null) {
      throw new RuntimeException("해당 Board ID는 존재하지 않습니다.");
    }

    // DTO를 엔티티로 변환하여 게시글 생성
    Post post = req.toEntity(board);
    createPostService.createPost(post);
  }

  // 게시글 수정
  public void updatePost(Long postId, UpdatePostReqDto req) {
    // 게시글이 삭제된 상태인지 체크
    readPostService.checkPostExists(postId);

    Post existingPost = readPostService.findPostById(postId);
    updatePostService.updatePost(existingPost, req);
  }

  // 게시글 삭제
  public void deletePost(Long postId) {
    // 게시글이 삭제된 상태인지 체크
    readPostService.checkPostExists(postId);

    deletePostService.deletePost(postId);
  }

  // 게시글 상세 조회
  public GetDetailPostRespDto getDetailPost(Long postId) {
    // 게시글이 삭제된 상태인지 체크
    readPostService.checkPostExists(postId);

    Post post = readPostService.findPostId(postId);
    return getDetailPostService.getDetailPost(postId);  // DTO 반환
  }
  // 게시글 조회 요청 처리 (검색어, 게시판 ID, 페이지 번호, 페이지 크기)
  public Page<Post> queryPosts(String keyword, Long boardId, int page, int size) {
    return readPostService.readPosts(keyword, boardId, page, size);
  }
}