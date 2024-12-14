package com.pawstime.pawstime.domain.user.facade;

import com.pawstime.pawstime.domain.Role.entity.Role;
import com.pawstime.pawstime.domain.Role.entity.repository.RoleRepository;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.service.create.CreateUserService;
import com.pawstime.pawstime.domain.user.service.dto.CustomUserInfoDto;
import com.pawstime.pawstime.domain.user.service.read.ReadUserService;
import com.pawstime.pawstime.global.jwt.util.JwtUtil;
import com.pawstime.pawstime.web.api.user.dto.request.LoginUserRequestDto;
import com.pawstime.pawstime.web.api.user.dto.request.UserCreateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacade {

  private final CreateUserService createUserService;
  private final ReadUserService readUserService;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;
  private final RoleRepository roleRepository;
  @Transactional
  public void createUser(@Valid UserCreateRequestDto dto) {

    try {
      //1.이메일 중복 확인
      if (readUserService.existsByEmail(dto.email())) {
        throw new BadCredentialsException("존재하는 이메일입니다.");
      }

    //역할 확인 및 변환
    Role role = roleRepository.findByRoleName(dto.roleName())
      .orElseThrow(() -> new IllegalArgumentException("잘못된 역할 이름입니다."));

  // User 엔티티 생성
    User user = dto.of(encoder.encode(dto.password()), role);

    // User 및 UserRole 저장
    createUserService.createUser(user, role);
  }catch (BadCredentialsException e) {
      log.error("이메일 중복 오류: {}", e.getMessage());
      throw e; // 이메일 중복 오류를 클라이언트에게 반환
    } catch (IllegalArgumentException e) {
      log.error("역할 이름 오류: {}", e.getMessage());
      throw e; // 잘못된 역할 이름 오류를 클라이언트에게 반환
    } catch (Exception e) {
      log.error("사용자 생성 중 오류 발생: {}", e.getMessage());
      throw new RuntimeException("사용자 생성에 실패했습니다.", e);
    }
  }

  public String login(LoginUserRequestDto dto) {
    User user = readUserService.findUserByEmail(dto.email());

    if (user == null) {
      throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
    }

    if (!encoder.matches(dto.password(), user.getPassword())) {
      throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
    }

    CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.of(user);
    return jwtUtil.createAccessToken(customUserInfoDto);
  }
}