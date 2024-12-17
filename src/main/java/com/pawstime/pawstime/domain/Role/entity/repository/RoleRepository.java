package com.pawstime.pawstime.domain.Role.entity.repository;

import com.pawstime.pawstime.domain.Role.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(String Name);

}
