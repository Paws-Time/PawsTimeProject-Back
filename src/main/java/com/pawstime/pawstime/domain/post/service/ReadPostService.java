package com.pawstime.pawstime.domain.post.service;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadPostService {

  private final BoardRepository boardRepository;
  private final UserRepository userRepository;

  // User를 ID로 조회하는 메서드
  public User findUserById(Long userId){

    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("해당 유저 id가 없습니다. : " + userId));
  }
  //Board를 ID로 조회하는 메서드
  public Board findBoardById(Long boardId){
    return boardRepository.findById(boardId)
        .orElseThrow(() -> new RuntimeException("해당 게시판 id가 없습니다. :" + boardId));
  }
}
