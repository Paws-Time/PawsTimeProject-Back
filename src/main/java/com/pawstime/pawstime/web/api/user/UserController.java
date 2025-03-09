package com.pawstime.pawstime.web.api.user;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.facade.UserFacade;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import com.pawstime.pawstime.web.api.user.dto.req.LoginUserReqDto;
import com.pawstime.pawstime.web.api.user.dto.req.UpdateNickReqDto;
import com.pawstime.pawstime.web.api.user.dto.req.UpdatePasswordReqDto;
import com.pawstime.pawstime.web.api.user.dto.req.UserCreateReqDto;
import com.pawstime.pawstime.web.api.user.dto.resp.GetUserRespDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "USER API", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserFacade userFacade;

  @Operation(summary = "회원 가입")
  @PostMapping
  public ResponseEntity<ApiResponse<Void>> createUser(@Valid @RequestBody UserCreateReqDto req) {

      userFacade.createUser(req);

      return ApiResponse.generateResp(Status.CREATE, "회원가입이 완료되었습니다.", null);
  }

  @Operation(summary = "로그인")
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginUserReqDto req) {

      String token = userFacade.login(req);

      return ApiResponse.generateResp(Status.SUCCESS, null, token);
  }

  @Operation(summary = "로그아웃", security = @SecurityRequirement(name = "bearerAuth"))
  @PostMapping("/logout")
  public ResponseEntity<ApiResponse<Void>> logoutUser(
      Authentication authentication, HttpServletRequest request
  ) {

      userFacade.logout(authentication, request);

      return ApiResponse.generateResp(Status.SUCCESS, "로그아웃 되었습니다.", null);
  }

  @Operation(summary = "userId를 통해 유저 정보 조회")
  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponse<GetUserRespDto>> getUserFromUserId(@PathVariable Long userId) {

    return ApiResponse.generateResp(Status.SUCCESS, null, userFacade.getUserFromUserId(userId));
  }

  @Operation(summary = "회원 탈퇴")
  @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId, Authentication authentication, HttpServletRequest request
  ){
      userFacade.deleteUser(userId, authentication, request);
      return ApiResponse.generateResp(Status.DELETE, "회원탈퇴가 완료되었습니다.", null);
  }

  @Operation(summary = "현재 로그인한 사용자의 닉네임 변경")
  @PutMapping("/me/nick")
  public ResponseEntity<ApiResponse<Void>> updateNick(
      @Valid @RequestBody UpdateNickReqDto updateNickReqDto,
      HttpServletRequest httpServletRequest) {
    userFacade.updateNick(updateNickReqDto, httpServletRequest);
    return ApiResponse.generateResp(Status.UPDATE, "닉네임 수정이 완료되었습니다.", null);
  }

  @Operation(summary = "현재 로그인한 사용자의 비밀번호 변경")
  @PutMapping("/me/password")
  public ResponseEntity<ApiResponse<Void>> updatePassword(
      @Valid @RequestBody UpdatePasswordReqDto updatePasswordReqDto,
      Authentication authentication,
      HttpServletRequest httpServletRequest
  ) {
    userFacade.updatePassword(updatePasswordReqDto, httpServletRequest);
    userFacade.logout(authentication, httpServletRequest);
    return ApiResponse.generateResp(Status.UPDATE, "비밀번호 수정이 완료되었습니다.", null);
  }
}
