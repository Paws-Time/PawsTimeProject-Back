package com.pawstime.pawstime.domain.board.service;

import com.pawstime.pawstime.domain.board.dto.resp.GetBoardRespDto;
import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadBoardService {

  private final BoardRepository boardRepository;

  public Board findByTitle(String title) {
    return boardRepository.findByTitleQuery(title);
  }

  public Board findById(Long boardId) {
    return boardRepository.findById(boardId).orElse(null);
  }
}
