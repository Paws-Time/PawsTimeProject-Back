package com.pawstime.pawstime.domain.board.facade;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.dto.req.UpdateBoardReqDto;
import com.pawstime.pawstime.domain.board.dto.resp.GetBoardRespDto;
import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.service.CreateBoardService;
import com.pawstime.pawstime.domain.board.service.ReadBoardService;
import com.pawstime.pawstime.global.exception.DuplicateException;
import com.pawstime.pawstime.global.exception.InvalidException;
import com.pawstime.pawstime.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    if (req.title() == null) {
      throw new InvalidException("게시판 제목은 필수 입력값입니다.");
    }

    Board existingBoard = readBoardService.findByTitle(req.title());

    if (existingBoard != null) {
      throw new DuplicateException("이미 존재하는 게시판입니다.");
    }

    createBoardService.createBoard(req.of());
  }

  @Transactional(readOnly = true)
  public GetBoardRespDto getBoard(Long boardId) {
    Board board = readBoardService.findByIdQuery(boardId);

    if (board == null) {
      throw new NotFoundException("존재하지 않는 게시판 ID입니다.");
    }

    return GetBoardRespDto.from(board);
  }

  @Transactional(readOnly = true)
  public Page<GetBoardRespDto> getBoardList(
      int pageNo, int pageSize, String sortBy, String direction
  ) {

    Pageable pageable = PageRequest
        .of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));

    return readBoardService.getBoardList(pageable).map(GetBoardRespDto::from);
  }

  public void deleteBoard(Long boardId) {
    Board board = readBoardService.findById(boardId);

    if (board == null) {
      throw new NotFoundException("존재하지 않는 게시판 ID입니다.");
    }
    if (board.isDelete()) {
      throw new NotFoundException("이미 삭제된 게시판입니다.");
    }

    board.softDelete();
    createBoardService.createBoard(board);
  }

  public void updateBoard(Long boardId, UpdateBoardReqDto req) {
    // 입력받은 boardId로 해당 게시판을 조회
    Board board = readBoardService.findById(boardId);

    if (board == null) {
      throw new NotFoundException("존재하지 않는 게시판 ID입니다.");
    }
    if (board.isDelete()) {
      throw new NotFoundException("이미 삭제된 게시판입니다.");
    }

    // 요청 받은 title이 비어있지 않으면 기존의 제목을 수정하고,
    // 비어있다면 기존 제목을 그대로 유지 (수정하지 않음)
    if (req.title() != null) {
      Board existingBoard = readBoardService.findByTitle(req.title());

      // 이미 동일한 제목을 사용하는 게시판이 존재하는지 확인
      if (existingBoard != null && !existingBoard.getBoardId().equals(boardId)) {
        throw new DuplicateException("이미 존재하는 게시판 제목입니다.");
      }

      board.updateTitle(req.title());
    }

    // 요청 받은 description이 비어있지 않으면 기존의 설명을 수정하고,
    // 비어있다면 기존 설명을 그대로 유지 (수정하지 않음)
    if (req.description() != null) {
      // description은 중복이 허용되므로 같은 값을 가진 board가 있는지 확인하지 않고 바로 수정
      board.updateDescription(req.description());
    }

    createBoardService.createBoard(board);
  }
}
