package com.pawstime.pawstime.domain.user.service.create;

import com.pawstime.pawstime.domain.Role.entity.Role;
import com.pawstime.pawstime.domain.user.entity.User;
import com.pawstime.pawstime.domain.user.entity.repository.UserRepository;
import com.pawstime.pawstime.domain.userrole.entity.UserRole;
import com.pawstime.pawstime.domain.userrole.entity.repository.UserRoleRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;

  public void createUser(User user, Role role){
    //user 저장
    userRepository.save(user);

    //UserRole 생성 및 저장
    UserRole userRole =  new UserRole(null, user, role, LocalDateTime.now());
    userRoleRepository.save(userRole);
  }
}
