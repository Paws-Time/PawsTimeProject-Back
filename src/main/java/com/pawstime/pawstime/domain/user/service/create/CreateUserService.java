package com.pawstime.pawstime.domain.user.service.create;

import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

  private final UserRepository userRepository;

  public void createUser(User user) {
    userRepository.save(user);
  }
}
