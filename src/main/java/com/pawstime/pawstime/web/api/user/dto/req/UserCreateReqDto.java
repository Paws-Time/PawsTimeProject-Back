package com.pawstime.pawstime.web.api.user.dto.req;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateReqDto(
    @Schema(example = "user1@naver.com")
    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "유효하지 않는 이메일 형식입니다.")
    String email,

    @Schema(example = "Qwer1234^^")
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하만 가능합니다.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).*$",
//                    "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).{8,}$"
            message = "비밀번호는 대/소문자, 숫자, 특수문자를 포함해야 합니다.")
    String password,

    @Schema(example = "user1")
    @NotBlank(message = "닉네임은 필수 입력입니다.")
    @Size(min = 2, max = 12, message = "닉네임은 2자 이상 12자 이하만 가능합니다.")
    String nick,

    @Schema(example = "USER")
    Role role
) {

  public User of(String password) {
    return User.builder()
        .email(email)
        .password(password)   // 암호화된 비밀번호를 넣음
        .nick(nick)
        .role(role != null ? role : Role.USER) // role에 값을 입력하지 않으면 기본값 USER로 설정
        .build();
  }
}
