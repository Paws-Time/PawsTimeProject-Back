package com.pawstime.pawstime.global.common;

import com.pawstime.pawstime.global.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ApiResponse<T> {

  private Status status;
  private String message;
  private T data;

  public ApiResponse(Status status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public static <T> ApiResponse<T> generateResp(Status status, String message, T data) {
    return new ApiResponse<>(status, message, data);
  }
}
