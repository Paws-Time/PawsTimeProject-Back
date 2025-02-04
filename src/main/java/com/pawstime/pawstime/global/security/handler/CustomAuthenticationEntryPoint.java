package com.pawstime.pawstime.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    log.error("Not Authenticated Request", authException);

    // ApiResponse 형태로 UNAUTHORIZED 응답구조 생성 (인증되지 않은 사용자가 접근시)
    String responseBody = objectMapper.writeValueAsString(
        ApiResponse.generateResp(Status.UNAUTHORIZED, "로그인 해주세요.", null).getBody()
    );

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(Status.UNAUTHORIZED.getHttpStatus().value());
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(responseBody);
  }
}
