package com.pawstime.pawstime.domain.post.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UpdatePostReqDto(
        @Schema(description = "게시글 제목", example = "우리 집 구리 보러오세요")
        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        String title,
        @Schema(description = "게시글 내용", example = "일상 내용")
        @NotBlank(message = "내용은 비어 있을 수 없습니다.")
        String content
) {
}
