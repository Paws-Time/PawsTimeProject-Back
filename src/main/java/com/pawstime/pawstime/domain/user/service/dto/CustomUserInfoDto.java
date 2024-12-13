package com.pawstime.pawstime.domain.user.service.dto;

import com.pawstime.pawstime.domain.Role.entity.Role;
import com.pawstime.pawstime.domain.user.entity.User;
import java.util.Set;
import lombok.Builder;

@Builder
public record CustomUserInfoDto(
    Long UserId,
    String email,
    String password,
    Set<Role> roles
) {

  public static CustomUserInfoDto of(User user) {
    return CustomUserInfoDto.builder()
        .UserId(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .roles(user.getRoles())
        .build();
  }
}
