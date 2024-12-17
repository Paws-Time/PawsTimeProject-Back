package com.pawstime.pawstime.domain.post.dto.req;

import com.pawstime.pawstime.domain.post.entity.Post.PostCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePostReqDto(
    @Schema(description = "게시글 제목", example = "우리 집 구리 보러오세요")
    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    String title,
    @Schema(description = "게시글 내용", example = "일상 내용")
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    String content,
    @NotNull(message = "카테고리는 반드시 지정해야 합니다.")
    PostCategory postCategory
) {
}
