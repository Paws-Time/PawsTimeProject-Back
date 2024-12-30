package com.pawstime.pawstime.domain.user.facade;

import com.pawstime.pawstime.domain.tokenBlacklist.service.TokenBlacklistService;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.service.create.CreateUserService;
import com.pawstime.pawstime.domain.user.service.dto.CustomUserInfoDto;
import com.pawstime.pawstime.domain.user.service.read.ReadUserService;
import com.pawstime.pawstime.global.exception.DuplicateException;
import com.pawstime.pawstime.global.exception.NotFoundException;
import com.pawstime.pawstime.global.exception.UnauthorizedException;
import com.pawstime.pawstime.global.jwt.util.JwtUtil;
import com.pawstime.pawstime.web.api.user.dto.req.LoginUserReqDto;
import com.pawstime.pawstime.web.api.user.dto.req.UserCreateReqDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class UserFacade {

  private final CreateUserService createUserService;
  private final ReadUserService readUserService;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;
  private final TokenBlacklistService tokenBlacklistService;

  public void createUser(UserCreateReqDto req) {
    try {
      User user = readUserService.findUserByEmail(req.email());

      if (user == null) {
        User newUser = req.of(encoder.encode(req.password()));
        createUserService.createUser(newUser);
      } else {
        throw new DuplicateException("존재하는 이메일 입니다.");
      }
    } catch (Exception e) {
      throw new DuplicateException("존재하는 이메일 입니다.");
    }
  }

  public String login(LoginUserReqDto req) {
    User user = readUserService.findUserByEmail(req.email());

    if (user == null) {
      throw new NotFoundException("존재하지 않는 이메일 입니다.");
    }

    if (!encoder.matches(req.password(), user.getPassword())) {
      throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
    }

    CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.of(user);
    return jwtUtil.createAccessToken(customUserInfoDto);
  }

  public void logout(Authentication authentication, HttpServletRequest request) {
    if (authentication == null) {
      throw new UnauthorizedException("인증 실패 : authentication is Null");
    }

    if (request.getHeader("Authorization") == null ||
        !request.getHeader("Authorization").startsWith("Bearer ")) {
      throw new UnauthorizedException("인증 실패 : Authorization 헤더가 없거나 형식이 올바르지 않습니다.");
    }

    String name = authentication.getName();
    log.info("** 로그아웃 요청한 사용자 : {} **", name);

    String token = request.getHeader("Authorization").substring(7);
    log.info("** token : {} **", token);

    Claims claims = jwtUtil.parseClaims(token);

    LocalDateTime expTime = claims.getExpiration()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    log.info("** expTime : {} **", expTime);

    tokenBlacklistService.createTokenBlacklist(token, expTime);
    request.getSession().removeAttribute("cart");
  }
}
