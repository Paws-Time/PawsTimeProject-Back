package com.pawstime.pawstime.domain.board.service;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.entity.repository.BoardRepository;
import com.pawstime.pawstime.domain.board.enums.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateBoardService {

  private final BoardRepository boardRepository;

  public void createBoard(Board board, BoardType boardType) {
    board.setBoardType(boardType);
    boardRepository.save(board);
  }
}
