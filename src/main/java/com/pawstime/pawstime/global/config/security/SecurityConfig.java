package com.pawstime.pawstime.global.config.security;

import com.pawstime.pawstime.global.jwt.filter.JwtFilter;
import com.pawstime.pawstime.global.jwt.util.JwtUtil;
import com.pawstime.pawstime.global.security.handler.CustomAccessDeniedHandler;
import com.pawstime.pawstime.global.security.handler.CustomAuthenticationEntryPoint;
import com.pawstime.pawstime.global.security.user.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final CustomUserDetailsService customUserDetailsService;
  private final JwtUtil jwtUtil;
  private final CustomAccessDeniedHandler accessDeniedHandler;
  private final CustomAuthenticationEntryPoint authenticationEntryPoint;

  // Swagger UI 및 API 문서에 대한 접근을 허용하는 경로 (모든 사용자에게 허용)
  private static final String[] AUTH_WHITELIST = {
    "/swagger-ui/**", "/swagger-ui-custom.html", "/swagger-ui.html",
    "/api-docs", "/api-docs/**", "/v3/api-docs/**"
  };

  // 관리자만 접근을 허용하는 경로
  private static final String[] ADMIN_ONLY = {

  };

  // 로그인 한 사용자(관리자 + 일반유저)만 접근을 허용하는 경로
  private static final String[] ADMIN_USER_ONLY = {
    "/users/logout"//, "/post/**/likes"
  };

  // 모든 사용자에게 접근을 허용하는 경로
  private static final String[] PUBLIC_ALL = {
    "/users", "/users/login"//, "/post/**/thumbnail", "/post/images/random"
  };

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .addFilterBefore(new JwtFilter(customUserDetailsService, jwtUtil), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(c -> c.authenticationEntryPoint((authenticationEntryPoint))
            .accessDeniedHandler(accessDeniedHandler))
        .authorizeHttpRequests(c -> c
            .requestMatchers(AUTH_WHITELIST).permitAll()  // 인증이 필요없는 url => 모두 허용
            .requestMatchers(ADMIN_ONLY).hasRole("ADMIN")
            .requestMatchers(ADMIN_USER_ONLY).hasAnyRole("ADMIN", "USER")
            .requestMatchers(PUBLIC_ALL).permitAll()

            .requestMatchers(HttpMethod.POST, "/boards").hasRole("ADMIN")   // 게시판 생성 => 관리자만 가능하도록 함
            .requestMatchers(HttpMethod.DELETE, "/boards/**").hasRole("ADMIN")   // 게시판 삭제 => 관리자만 가능하도록 함
            .requestMatchers(HttpMethod.PUT, "/boards/**").hasRole("ADMIN")   // 게시판 수정 => 관리자만 가능하도록 함
            .requestMatchers(HttpMethod.GET, "/boards", "/boards/**").permitAll()   // 게시판 목록조회,상세조회 => 모두 접근 가능

            //.requestMatchers(HttpMethod.POST, "/post", "/post/**").hasAnyRole("ADMIN", "USER")
            //.requestMatchers(HttpMethod.PUT, "/post/**", "/post/**/images").hasAnyRole("ADMIN", "USER")
            //.requestMatchers(HttpMethod.DELETE, "/post/**").hasAnyRole("ADMIN", "USER")
            //.requestMatchers(HttpMethod.GET, "/post", "/post/**", "/post/**/images").permitAll()

            .anyRequest().authenticated())  // 그 외 요청은 로그인된 사용자만 접근 가능
            //.anyRequest().permitAll())   // 그 외 요청 모두 접근 가능 (로그인을 하지 않아도 모든 요청을 허용 => 보안을 위해 하지 않는 것이 좋다)
        .build();
  }
}
