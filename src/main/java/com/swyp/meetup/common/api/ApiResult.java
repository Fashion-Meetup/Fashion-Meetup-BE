package com.swyp.meetup.common.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResult {
    private int code;
    private String status;
    private String message;

    private ApiResult(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public static ApiResult set(ResponseCode code) {
        return ApiResult.builder()
                .code(code.getCode())
                .status(code.getStatus())
                .message(code.getMessage())
                .build();
    }
}
