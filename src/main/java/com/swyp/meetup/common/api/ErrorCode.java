package com.swyp.meetup.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ResponseCode{

    INTERNAL_SERVER_ERROR(500,"SERVER ERROR", "내부 서버 에러 발생");

    private int code;
    private String status;
    private String message;
}
