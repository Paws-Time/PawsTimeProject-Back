package com.pawstime.pawstime.global.handler;

import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.ResponseCode;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import com.pawstime.pawstime.web.api.user.dto.resp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

  //일반 Exception을 처리하는 메서드
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Response<String>> handleAllExceptions(Exception e, WebRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new Response<String>(ResponseCode.GENERAL_ERROR, e.getMessage()));
  }
  //커스텀 예외를 처리하는 메서드
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<String>> handleCustomException(CustomException e) {
    Status status;

    //예외 이름에서 상태 값 추출
    try{
      status=Status.valueOf(e.getClass().getSimpleName().replace("Exception", "").toUpperCase());
    }
    catch (IllegalArgumentException ex){
      status = Status.ERROR; //상태가 없으면 기본값을 설정
    }
    log.error("Custom exception: {}", status);
    return ApiResponse.generateResp(status, e.getMessage(), null);
  }
}
