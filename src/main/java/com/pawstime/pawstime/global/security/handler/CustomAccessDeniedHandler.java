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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {

    log.error("No Authorities", accessDeniedException);

    // ApiResponse 형태로 FORBIDDEN 응답구조 생성 (로그인은 했지만 권한이 없는 경우)
    String responseBody = objectMapper.writeValueAsString(
        ApiResponse.generateResp(Status.FORBIDDEN, "권한이 없습니다.", null).getBody()
    );

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(Status.FORBIDDEN.getHttpStatus().value());
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(responseBody);
  }
}
