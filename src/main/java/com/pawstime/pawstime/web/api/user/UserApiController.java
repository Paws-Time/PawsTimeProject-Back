package com.pawstime.pawstime.web.api.user;

import com.pawstime.pawstime.domain.user.facade.UserFacade;
import com.pawstime.pawstime.global.jwt.util.JwtUtil;
import com.pawstime.pawstime.web.api.user.dto.request.LoginUserRequestDto;
import com.pawstime.pawstime.web.api.user.dto.request.UserCreateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "USER API", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserApiController {

  private final UserFacade userFacade;
  private final JwtUtil jwtUtil;


  @Operation(summary = "회원 신규 가입")
  @PostMapping("/user")
  public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequestDto dto) {
    userFacade.createUser(dto);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "로그인")
  @PostMapping("/login")
  public ResponseEntity<?> loginrUser(@Valid @RequestBody LoginUserRequestDto dto) {
    String token = userFacade.login(dto);
    return ResponseEntity.status(HttpStatus.OK).body(token);
  }
}
