package com.pawstime.pawstime.web.api.user.dto.resp;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.enums.Role;
import lombok.Builder;

@Builder
public record GetUserRespDto(
    Long userId,
    String nick,
    Role role
) {

  public static GetUserRespDto from(User user) {
    return GetUserRespDto.builder()
        .userId(user.getUserId())
        .nick(user.getNick())
        .role(user.getRole())
        .build();
  }
}
