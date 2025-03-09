package com.pawstime.pawstime.web.api.user.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePasswordReqDto(
    @Schema(description = "기존 비밀번호", example = "Qwer1234^^")
    @NotBlank(message = "기존 비밀번호를 입력하세요.")
    String currentPassword,

    @Schema(description = "변경할 비밀번호", example = "Qwer12345^^")
    @NotBlank(message = "변경할 비밀번호를 입력하세요.")
    @Size(min = 8, max = 20, message = "새로운 비밀번호는 8자 이상 20자 이하만 가능합니다.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).*$",
        message = "새로운 비밀번호는 대/소문자, 숫자, 특수문자를 포함해야 합니다.")
    String newPassword
) {

}
