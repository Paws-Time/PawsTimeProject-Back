package com.pawstime.pawstime.domain.user.service.dto;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.enums.Role;
import lombok.Builder;

@Builder
public record CustomUserInfoDto(
    Long userId,
    String email,
    String password,
    Role role
) {

  public static CustomUserInfoDto of(User user) {
    return CustomUserInfoDto.builder()
        .userId(user.getUserId())
        .email(user.getEmail())
        .password(user.getPassword())
        .role(user.getRole())
        .build();
  }
}
