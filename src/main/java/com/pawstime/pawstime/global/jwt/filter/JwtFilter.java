package com.pawstime.pawstime.global.jwt.filter;

import com.pawstime.pawstime.domain.tokenBlacklist.service.TokenBlacklistService;
import com.pawstime.pawstime.global.exception.UnauthorizedException;
import com.pawstime.pawstime.global.jwt.util.JwtUtil;
import com.pawstime.pawstime.global.security.user.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final CustomUserDetailsService customUserDetailsService;
  private final TokenBlacklistService tokenBlacklistService;
  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authorization = request.getHeader("Authorization");
//
//    if (authorization != null && authorization.startsWith("Bearer ")) {
//      String token = authorization.substring(7);
//
//      // 토큰이 블랙리스트에 들어있는지 확인
//      if (tokenBlacklistService.isBlacklisted(token)) {
//        throw new UnauthorizedException("로그아웃 처리된 토큰입니다. 다시 로그인해주세요");
//      }
//
//      if (jwtUtil.validateToken(token)) {
//        // String userId = jwtUtil.getUserId(token);
//        Long userId = jwtUtil.getUserId(token);
//
//        System.out.println("*****"+userId);
//
//        // UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
//        UserDetails userDetails = customUserDetailsService.loadUserByUserId(userId);
//
//        if (userDetails != null) {
//          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//              userDetails, null, userDetails.getAuthorities());
//
//          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//      }
//    }
    filterChain.doFilter(request, response);
  }
}
