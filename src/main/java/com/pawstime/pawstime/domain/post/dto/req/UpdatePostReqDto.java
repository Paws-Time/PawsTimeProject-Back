package com.pawstime.pawstime.domain.post.dto.req;

import com.pawstime.pawstime.domain.post.entity.Post.PostCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePostReqDto(
    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    String title,
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    String content,
    @NotNull(message = "카테고리는 반드시 지정해야 합니다.")
    PostCategory postCategory
) {
}
