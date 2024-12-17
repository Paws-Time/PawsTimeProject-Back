package com.pawstime.pawstime.domain.board.facade;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.domain.board.dto.resp.GetBoardRespDto;
import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.board.service.CreateBoardService;
import com.pawstime.pawstime.domain.board.service.ReadBoardService;
import java.util.List;
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
    Board board = readBoardService.findByTitle(req.title());

    if (board != null) {
      throw new RuntimeException("이미 존재하는 게시판입니다.");
    }

    createBoardService.createBoard(req.of());
  }

  public GetBoardRespDto getBoard(Long boardId) {
    Board board = readBoardService.findById(boardId);

    if (board == null) {
      throw new RuntimeException("해당 ID의 게시판은 존재하지 않습니다.");
    }

    return GetBoardRespDto.from(board);
  }

  public Page<GetBoardRespDto> getBoardList(int pageNo, int pageSize, String sortBy, String direction) {

    Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));

    return readBoardService.getBoardList(pageable).map(GetBoardRespDto::from);
  }

  public void deleteBoard(Long boardId) {
    Board board = readBoardService.findById(boardId);

    if (board == null) {
      throw new RuntimeException("해당 ID의 게시판은 존재하지 않습니다.");
    }
    if (board.isDelete()) {
      throw new RuntimeException("이미 삭제된 게시판입니다.");
    }

    board.softDelete();
    createBoardService.createBoard(board);
  }
}
