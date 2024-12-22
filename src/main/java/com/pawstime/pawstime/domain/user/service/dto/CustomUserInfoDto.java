package com.pawstime.pawstime.domain.user.service.dto;


import com.pawstime.pawstime.domain.role.entity.Role;
import com.pawstime.pawstime.domain.user.entity.User;
import java.util.Set;
import lombok.Builder;

@Builder
public record CustomUserInfoDto(
    Long userId,
    String email,
    String password,
    Set<Role> roles
) {

  public static CustomUserInfoDto of(User user) {
    return CustomUserInfoDto.builder()
        .userId(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .roles(user.getRoles())
        .build();
  }
}