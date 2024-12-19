package com.pawstime.pawstime.global.enums;

import lombok.Getter;

@Getter
public enum Status {

  // 성공 상태
  SUCCESS("Success", "요청이 성공적으로 처리된 경우"),
  CREATE("Create", "리소스가 성공적으로 생성된 경우"),
  UPDATE("Update", "리소스가 성공적으로 수정된 경우"),
  DELETE("Delete", "리소스가 성공적으로 삭제된 경우"),

  // 실패 상태
  INVALID("Invalid", "요청 데이터가 잘못된 경우"),
  DUPLICATE("Duplicate", "이미 존재하는 데이터로 새롭게 생성하려는 경우"),
  NOTFOUND("Not Found", "요청한 리소스가 존재하지 않는 경우"),
  UNAUTHORIZED("Unauthorized", "인증되지 않은 사용자가 접근하려는 경우"),
  FORBIDDEN("Forbidden", "인증은 되었지만 권한이 없는 경우"),

  // 에러 상태
  ERROR("Error", "예상치 못한 예외가 발생한 경우");

  private final String code;
  private final String description;

  Status(String code, String description) {
    this.code = code;
    this.description = description;
  }
}
