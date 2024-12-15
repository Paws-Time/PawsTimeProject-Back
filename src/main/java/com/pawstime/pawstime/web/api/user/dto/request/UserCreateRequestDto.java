package com.pawstime.pawstime.web.api.user.dto.request;

import com.pawstime.pawstime.domain.Role.entity.Role;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.userrole.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public record UserCreateRequestDto(
    @NotBlank
    String email,
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()\\-+=]).{8,}$")
    String password,
    @NotBlank
    String nick,
    String roleName // String role로 받되, of에서 Role객체로 변환함
) {

  public User of(String password, Role role) {
    return User.builder()
        .email(email)
        .nick(nick)
        .password(password)
        .userRoles(new HashSet<>(Set.of(new UserRole(null, null, role, LocalDateTime.now())))) // UserRole 생성
        .build();
  }
}