package com.pawstime.pawstime.domain.user.entity;

import com.pawstime.pawstime.domain.Role.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String nick;

  private String password;

  @ManyToMany
  @JoinTable(
      name = "user_roles", // 중간 테이블 이름
      joinColumns = @JoinColumn(name = "user_id"), // User 테이블의 외래키
      inverseJoinColumns = @JoinColumn(name = "role_id") // Role 테이블의 외래키
  )
  private Set<Role> roles = new HashSet<>();


}
