package com.pawstime.pawstime.domain.board.controller;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.facade.BoardFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Board", description = "게사판 API")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardFacade boardFacade;

  @Operation(summary = "게시판 생성", description = "새로운 게시판을 생성할 수 있습니다.")
  @PostMapping("/boards")
  public ResponseEntity<String> createBoard(@RequestBody CreateBoardReqDto req) {
    try {
      boardFacade.createBoard(req);

      return ResponseEntity.ok().body("게시판 생성이 완료되었습니다.");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("게시판 생성 중 오류가 발생하였습니다 : " + e.getMessage());
    }
  }
}
