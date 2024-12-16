package com.pawstime.pawstime.global.security.user.service;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import com.pawstime.pawstime.domain.user.service.dto.CustomUserInfoDto;
import com.pawstime.pawstime.global.security.user.CustomUserDetails;
import com.pawstime.pawstime.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    // 이메일을 이용해서 사용자 조회

    if (user == null) {
      throw new UsernameNotFoundException("존재하지 않는 유저입니다.");
    }
    CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.of(user);
    return new CustomUserDetails(customUserInfoDto);
  }
}

