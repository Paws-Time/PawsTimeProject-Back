package com.pawstime.pawstime.domain.user.service.dto;

import com.pawstime.pawstime.domain.Role.entity.Role;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.userrole.entity.UserRole;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record CustomUserInfoDto(
    Long userId,
    String email,
    String password,
    Set<Role> roles
) {

  public static CustomUserInfoDto of(User user) {
    //UserRole에서 Role을 추출하여 Set<Role>을 생성
    Set<Role> roles = user.getUserRoles().stream()
        .map(UserRole::getRole)
        .collect(Collectors.toSet());
    return CustomUserInfoDto.builder()
        .userId(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .roles(roles)
        .build();
  }
}
