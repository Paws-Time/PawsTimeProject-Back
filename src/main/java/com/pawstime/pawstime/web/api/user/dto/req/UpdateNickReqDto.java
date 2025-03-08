package com.pawstime.pawstime.web.api.user.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNickReqDto(
    @Schema(description = "변경할 닉네임", example = "user1")
    @NotBlank(message = "변경할 닉네임을 입력하세요.")
    @Size(min = 2, max = 12, message = "닉네임은 2자 이상 12자 이하만 가능합니다.")
    String nick
) {

}
