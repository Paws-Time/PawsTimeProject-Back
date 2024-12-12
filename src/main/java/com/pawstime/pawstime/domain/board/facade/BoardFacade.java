package com.pawstime.pawstime.domain.board.facade;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.service.CreateBoardService;
import com.pawstime.pawstime.domain.board.service.ReadBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class BoardFacade {

  private final ReadBoardService readBoardService;
  private final CreateBoardService createBoardService;

  public void createBoard(CreateBoardReqDto req) {
    Board board = readBoardService.findByTitle(req.title());

    if (board != null) {
      throw new RuntimeException("이미 존재하는 게시판입니다.");
    }

    createBoardService.createBoard(req.of());
  }
}
