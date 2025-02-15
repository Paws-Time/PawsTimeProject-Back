package com.pawstime.pawstime.global.security.user.service;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import com.pawstime.pawstime.domain.user.service.dto.CustomUserInfoDto;
import com.pawstime.pawstime.global.exception.NotFoundException;
import com.pawstime.pawstime.global.security.user.CustomUserDetails;
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

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    User user = userRepository.findUserByEmail(email);
//    CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.of(user);
//    return new CustomUserDetails(customUserInfoDto);

    throw new UnsupportedOperationException("이 서비스는 userId 기반으로 동작합니다.");
  }
  // 토큰에 들어있는 이메일을 기반으로 user를 찾는 방식에서 userid를 기반으로 찾는 방식으로 바꿈

  public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
    User user = userRepository.findUserByUserIdQuery(userId);
    CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.of(user);
    return new CustomUserDetails(customUserInfoDto);
  }
}
