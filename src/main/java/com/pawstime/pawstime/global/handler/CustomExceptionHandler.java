package com.pawstime.pawstime.global.handler;

import com.pawstime.pawstime.domain.board.dto.req.CreateBoardReqDto;
import com.pawstime.pawstime.global.common.ApiResponse;
import com.pawstime.pawstime.global.enums.ResponseCode;
import com.pawstime.pawstime.global.enums.Status;
import com.pawstime.pawstime.global.exception.CustomException;
import com.pawstime.pawstime.global.exception.InvalidException;
import com.pawstime.pawstime.web.api.user.dto.resp.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
//@ControllerAdvice
@RestControllerAdvice
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

  // 유효성 검사 실패 예외 처리
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    // 첫 번째 에러 메시지를 추출
    String errorMessage = ex.getBindingResult()
        .getAllErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .findFirst() // 첫 번째 에러만 반환
        .orElse("잘못된 요청입니다.");

    log.error("Validation error: {}", errorMessage);
    return ApiResponse.generateResp(Status.INVALID, errorMessage, null);
  }

  @ExceptionHandler(InvalidException.class)
  public ResponseEntity<ApiResponse<String>> handleInvalidException(InvalidException ex) {
    String errorMessage = ex.getMessage();  // 예외 메시지를 그대로 사용

    // 로그 기록
    log.error("Invalid data error: {}", ex.getMessage());

    return ApiResponse.generateResp(Status.INVALID, errorMessage, null);
  }
}
