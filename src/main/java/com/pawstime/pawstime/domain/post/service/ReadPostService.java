package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.post.entity.Post;
import com.pawstime.pawstime.domain.post.entity.repository.PostRepository;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadPostService {

  private final BoardRepository boardRepository;
  private final UserRepository userRepository;
  private final PostRepository postRepository;

  /*User를 ID로 조회하는 메서드
  public User findUserById(Long userId){

    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("해당 유저 id가 없습니다. : " + userId));
  }*/

  //Board를 ID로 조회하는 메서드
  public Board findBoardById(Long boardId){
    return boardRepository.findById(boardId)
        .orElseThrow(() -> new RuntimeException("해당 게시판 id가 없습니다. :" + boardId));
  }
  //postId를 조회하는 메서드
  public Post findById(Long postId){
    return postRepository.findById(postId)
      .orElseThrow(() ->new RuntimeException("해당 게시글 id가 없습니다. " + postId));
  }
}
