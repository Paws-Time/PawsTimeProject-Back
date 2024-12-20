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
  private final GetListPostService getListPostService;

  public Post findPostId(Long postId){
    return postRepository.findById(postId)
            .orElseThrow(()->new RuntimeException("해당 포스트 id가 없습니다. : " + postId));
  }

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

  // 게시글 조회 요청 처리
  public Page<Post> readPosts(String keyword, Long boardId, int page, int size) {
    return getListPostService.getPosts(keyword, boardId, page, size);
  }
}
