package com.pawstime.pawstime.domain.board.dto.resp;

import com.pawstime.pawstime.domain.board.entity.Board;

import java.time.LocalDateTime;

import com.pawstime.pawstime.domain.board.enums.BoardType;
import lombok.Builder;

@Builder
public record GetBoardRespDto(
        Long boardId,
        String title,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        BoardType boardType

) {

    public static GetBoardRespDto from(Board board) {
        return GetBoardRespDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .description(board.getDescription())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .boardType(board.getBoardType())
                .build();
    }
}
