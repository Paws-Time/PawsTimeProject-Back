package com.pawstime.pawstime.web.api.user.dto.req;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateReqDto(
    @Schema(example = "user1@naver.com")
    @NotBlank
    String email,

    @Schema(example = "Qwer1234^^")
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).{8,}$")
    String password,

    @Schema(example = "user1")
    @NotBlank
    String nick
) {

  public User of(String password) {
    return User.builder()
        .email(email)
        .password(password)
        .nick(nick)
        .role(Role.valueOf("USER"))
        .build();
  }
}
