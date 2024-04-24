package com.swyp.meetup.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ResponseCode{

    INTERNAL_SERVER_ERROR(500,"SERVER ERROR", "내부 서버 에러 발생"),
    UNAUTHORIZED(401, "UNAUTHORIZED","인증 정보가 잘못되었습니다."),
    FORBIDDEN(403,"FORBIDDEN","접근 권한이 없습니다.");

    private int code;
    private String status;
    private String message;
}
