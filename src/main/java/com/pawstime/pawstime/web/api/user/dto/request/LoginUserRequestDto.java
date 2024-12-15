package com.pawstime.pawstime.web.api.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequestDto(
    @NotBlank
    String email,
    @NotBlank
    String password
) {

}
