package com.pawstime.pawstime.web.api.user.dto.resp;

import com.pawstime.pawstime.global.enums.ResponseCode;

public class Response<T> {

  private ResponseCode code;
  private T data;

  public Response(ResponseCode responseCode, T message) {
    this.code = responseCode;
    this.data = message;
  }
}
