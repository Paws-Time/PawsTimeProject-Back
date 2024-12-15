package com.pawstime.pawstime.domain.board.dto.resp;

import com.pawstime.pawstime.domain.board.entity.Board;
import lombok.Builder;

@Builder
public record GetBoardRespDto(
    Long boardId,
    String title,
    String description,
    boolean isDelete
) {

  public static GetBoardRespDto from(Board board) {
    return GetBoardRespDto.builder()
        .boardId(board.getBoardId())
        .title(board.getTitle())
        .description(board.getDescription())
        .isDelete(board.isDelete())
        .build();
  }
}
