package com.pawstime.pawstime.domain.board.dto.resp;

import com.pawstime.pawstime.domain.board.entity.Board;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetBoardRespDto(
    Long boardId,
    String title,
    String description,
    boolean isDelete,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

  public static GetBoardRespDto from(Board board) {
    return GetBoardRespDto.builder()
        .boardId(board.getBoardId())
        .title(board.getTitle())
        .description(board.getDescription())
        .isDelete(board.isDelete())
        .createdAt(board.getCreatedAt())
        .updatedAt(board.getUpdatedAt())
        .build();
  }
}
