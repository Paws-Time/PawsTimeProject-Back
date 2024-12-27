package com.pawstime.pawstime.domain.user.service.read;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadUserService {

  private final UserRepository userRepository;

  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }
}
