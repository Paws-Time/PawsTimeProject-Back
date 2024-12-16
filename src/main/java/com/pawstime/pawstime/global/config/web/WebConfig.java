package com.pawstime.pawstime.global.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            // addMapping : CORS를 적용할 URL 패턴 정의 ("/**"는 와일드 카드)
        .allowedOrigins("http://15.164.2.252:8080")
        // allowedOrigins : 자원 공유를 허락할 origin을 지정
        // (한 번에 여러 origin을 설정하거나 "*"로 모든 origin을 허락할 수도 있다)
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        // allowedMethods : 허용할 HTTP method를 지정 ("*"로 모든 method 허용 가능)
        .allowedHeaders("*");
        // allowedHeaders : 클라이언트 측의 CORS 요청에 허용되는 헤더를 지정
  }
}
