package com.pawstime.pawstime.domain.post2.dto.req;

import com.pawstime.pawstime.domain.board.entity.Board;
import com.pawstime.pawstime.domain.post2.entity.Post2;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreatePost2ReqDto(
        @Schema(description = "게시글 제목", example = "정보 게시판 입니다.")
        @NotBlank(message = "제목은 필수 입력값입니다.")
        String title,           // 제목

        @Schema(description = "게시글 내용", example = "정보 게시판")
        @NotBlank(message = "내용은 필수 입력값입니다.")
        String content,         // 내용


        @Schema(description = "게시판 ID", example = "2")
        Long boardId,        // 게시판 ID

        String name,        // 사업장명
        String address,     // 도로명주소
        Double latitude,    // 좌표정보(x)
        Double longitude,   // 좌표정보(y)
        String category,    // 업종구분명
        int regionCode      // 지역번호
) {
    public Post2 toEntity(Board board) {
        return Post2.builder()
                .title(this.title)
                .content(this.content)
                .board(board)
                .name(this.name)
                .address(this.address)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .category(this.category)
                .regionCode(this.regionCode)
                .build();
    }
}
