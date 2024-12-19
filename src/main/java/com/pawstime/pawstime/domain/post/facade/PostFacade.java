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

  public Page<GetListPostRespDto> getPosts(Long boardId, String titleKeyword, String contentKeyword, String sortBy, Pageable pageable) {
    Page<Post> posts;

    // 제목과 내용에서 검색
    if (titleKeyword != null || contentKeyword != null) {
      posts = postRepository.findByTitleContainingOrContentContainingAndIsDeleteFalse(titleKeyword, contentKeyword, pageable);
    } else if (boardId != null) {
      // 게시판 ID 기준으로 게시글 조회
      posts = postRepository.findByBoard_boardIdAndIsDeleteFalse(boardId, pageable);
    } else {
      // 정렬 기준에 맞춰 게시글 조회
      switch (sortBy) {
        case "views":
          posts = postRepository.findAllByIsDeleteFalseOrderByViewsDesc(pageable); // 조회수 내림차순
          break;
        case "title":
          posts = postRepository.findAllByIsDeleteFalseOrderByTitleAsc(pageable); // 가나다순
          break;
        case "latest":
        default:
          posts = postRepository.findAllByIsDeleteFalseOrderByCreatedAtDesc(pageable); // 최신순
          break;
      }
    }

    // 엔티티(Post)에서 DTO(GetListPostRespDto)로 변환하여 반환
    return posts.map(post -> GetListPostRespDto.builder()
            .id(post.getPostId())
            .title(post.getTitle())
            .contentPreview(post.getContent().length() > 100 ? post.getContent().substring(0, 100) + "..." : post.getContent())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .views(post.getViews())
            .likesCount(post.getLikesCount())
            .category(post.getCategory().name())
            .build());
  }

}