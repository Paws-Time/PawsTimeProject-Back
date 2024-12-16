package com.pawstime.pawstime.domain.user.entity.repository;

import com.pawstime.pawstime.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
  boolean existsByEmail(String email);
}
