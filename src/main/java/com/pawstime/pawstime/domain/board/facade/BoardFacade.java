package com.pawstime.pawstime.domain.board.facade;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.service.CreateBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class BoardFacade {

  private final CreateBoardService createBoardService;

  public void createBoard(CreateBoardReqDto req) {
    createBoardService.createBoard(req.of());
  }
}
