package com.pawstime.pawstime.domain.board.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateBoardReqDto(
    @Schema(description = "변경하고자 하는 게시판 제목",
            example = "변경하고자하는 게시판 제목(입력하지 않을 경우 기존값 그대로 유지)")
    String title,

    @Schema(description = "변경하고자 하는 게시판 설명",
            example = "변경하고자하는 게시판 설명(입력하지 않을 경우 기존값 그대로 유지)")
    String description
) {

}
