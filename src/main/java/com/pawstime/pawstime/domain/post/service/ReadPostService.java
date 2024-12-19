package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.post.dto.resp.GetListPostRespDto;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadPostService {

  private final BoardRepository boardRepository;
  private final PostRepository postRepository;

  // Board를 ID로 조회하는 메서드
  public Board findBoardById(Long boardId) {
    return boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("해당 게시판 id가 없습니다. :" + boardId));
  }

  // postId를 조회하는 메서드 (삭제된 포스트도 제외해야 함)
  public Post findPostById(Long postId) {
    return postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("해당 게시글 id가 없습니다. " + postId));
  }

  // 포스트가 존재하는지 확인하고, 이미 삭제된 포스트인지 체크
  public void checkPostExists(Long postId) {
    Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("포스트가 존재하지 않습니다."));

    if (post.isDelete()) {
      throw new IllegalArgumentException("이미 삭제된 포스트입니다.");
    }
  }

  // 게시글 조회 (특정 게시판, 제목/내용 키워드 검색 포함, 페이징 처리)
  public Page<GetListPostRespDto> getPosts(Long boardId, String titleKeyword, String contentKeyword, String sortField, Pageable pageable) {
    Page<Post> posts;

    if (boardId != null) {
      // 특정 게시판에 대해 제목과 내용 검색
      posts = postRepository.findByTitleContainingOrContentContainingAndBoard_boardIdAndIsDeleteFalse(
              titleKeyword, contentKeyword, boardId, pageable);
    } else {
      // 게시판 ID 없이 제목과 내용 검색
      posts = postRepository.findByTitleContainingOrContentContainingAndIsDeleteFalse(
              titleKeyword, contentKeyword, pageable);
    }

    return posts.map(GetListPostRespDto::from);  // GetListPostRespDto.from() 사용
  }

  // 특정 게시판의 게시글 조회 (삭제되지 않은 게시글만)
  public Page<Post> getPostsByBoard(Long boardId, Pageable pageable) {
    if (boardId != null) {
      return postRepository.findByBoard_boardIdAndIsDeleteFalse(boardId, pageable);
    } else {
      return postRepository.findAll(pageable);
    }
  }

  // 제목과 내용에서 검색 (boardId가 있을 경우 해당 게시판에 한정)
  public Page<Post> searchPosts(String titleKeyword, String contentKeyword, Long boardId, Pageable pageable) {
    if (boardId != null) {
      return postRepository.findByTitleContainingOrContentContainingAndBoard_boardIdAndIsDeleteFalse(
              titleKeyword, contentKeyword, boardId, pageable);
    } else {
      return postRepository.findByTitleContainingOrContentContainingAndIsDeleteFalse(
              titleKeyword, contentKeyword, pageable);
    }
  }

  // 최신순 (작성일 기준, 삭제되지 않은 게시글만)
  public Page<Post> getPostsSortedByCreatedAt(Pageable pageable) {
    return postRepository.findAllByIsDeleteFalseOrderByCreatedAtDesc(pageable);
  }

  // 조회수 기준 내림차순 (삭제되지 않은 게시글만)
  public Page<Post> getPostsSortedByViews(Pageable pageable) {
    return postRepository.findAllByIsDeleteFalseOrderByViewsDesc(pageable);
  }

  // 가나다순 (제목 기준, 삭제되지 않은 게시글만)
  public Page<Post> getPostsSortedByTitle(Pageable pageable) {
    return postRepository.findAllByIsDeleteFalseOrderByTitleAsc(pageable);
  }

  // 게시글을 ID로 조회하는 메서드 (삭제되지 않은 포스트만)
  public Post findPostId(Long postId) {
    return postRepository.findByPostIdAndIsDeleteFalse(postId)
            .orElseThrow(() -> new RuntimeException("해당 게시글 id가 없습니다. " + postId));
  }

}
