package com.pawstime.pawstime.domain.post.service.read;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReadPostService {

  private final BoardRepository boardRepository;
  private final PostRepository postRepository;


  public Post findPostId(Long postId){
    return postRepository.findById(postId)
            .orElse(null);
  }

  // Board를 ID로 조회하는 메서드
  public Board findBoardById(Long boardId) {
    return boardRepository.findById(boardId)
            .orElse(null);
  }

  // 게시글 ID로 게시글 조회
  public Post findPostById(Long postId) {
    return postRepository.findById(postId)
            .orElse(null); // 예외 처리는 Facade에서 처리
  }

  public Page<Post> findByUser(Pageable pageable, User user) {
    return postRepository.findByUser(pageable, user);
  }
}
