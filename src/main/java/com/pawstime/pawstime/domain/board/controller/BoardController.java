package com.pawstime.pawstime.domain.board.controller;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.dto.req.UpdateBoardReqDto;
import com.pawstime.pawstime.domain.board.dto.resp.GetBoardRespDto;
import com.pawstime.pawstime.domain.board.facade.BoardFacade;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Board", description = "게사판 API")
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardFacade boardFacade;

  @Operation(summary = "게시판 생성", description = "새로운 게시판을 생성할 수 있습니다.")
  @PostMapping
  public ResponseEntity<ApiResponse<Void>> createBoard(@Valid @RequestBody CreateBoardReqDto req) {

      boardFacade.createBoard(req);

      return ApiResponse.generateResp(
          Status.CREATE, "게시판 생성이 완료되었습니다.", null);
  }

  @Operation(summary = "게시판 상세 조회",
      description = "board_id를 입력하면 title, description을 조회할 수 있습니다.")
  @GetMapping("/{boardId}")
  public ResponseEntity<ApiResponse<GetBoardRespDto>> getBoard(@PathVariable Long boardId) {

      return ApiResponse.generateResp(
          Status.SUCCESS, null, boardFacade.getBoard(boardId));
  }

  @Operation(summary = "게시판 목록 조회", description = "생성되어있는 모든 게시판을 조회합니다.")
  @GetMapping
  public ResponseEntity<ApiResponse<List<GetBoardRespDto>>> getBoardList(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "10") int pageSize,
      @RequestParam(defaultValue = "createdAt") String sortBy,
      @RequestParam(defaultValue = "DESC") String direction
  ) {

      return ApiResponse.generateResp(Status.SUCCESS, null,
          boardFacade.getBoardList(pageNo, pageSize, sortBy, direction).getContent());
  }

  @Operation(summary = "게시판 삭제", description = "선택한 게시판을 삭제합니다.")
  @DeleteMapping("/{boardId}")
  public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable Long boardId) {

      boardFacade.deleteBoard(boardId);

      return ApiResponse.generateResp(Status.DELETE, "게시판이 삭제되었습니다.", null);
  }

  @Operation(summary = "게시판 수정", description = "선택한 게시판의 제목, 설명을 수정할 수 있습니다.")
  @PutMapping("/{boardId}")
  public ResponseEntity<ApiResponse<Void>> updateBoard(
      @PathVariable Long boardId, @RequestBody UpdateBoardReqDto req) {

      boardFacade.updateBoard(boardId, req);

      return ApiResponse.generateResp(
          Status.UPDATE, "게시판 수정이 완료되었습니다.", null);
  }
}
