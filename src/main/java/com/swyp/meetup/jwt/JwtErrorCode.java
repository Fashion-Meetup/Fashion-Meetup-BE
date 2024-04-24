package com.swyp.meetup.jwt;

import com.swyp.meetup.common.api.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtErrorCode implements ResponseCode {

    JWT_EXPIRED(401,"UNAUTHORIZED", "만료된 토큰입니다."),
    JWT_MALFORMED(401,"UNAUTHORIZED","잘못된 형태의 JWT입니다."),
    JWT_UNSUPPORTED(401,"UNAUTHORIZED","지원하지 않는 토큰 형식입니다."),
    JWT_ILLEGAL(401,"UNAUTHORIZED","토큰 내에 부적절한 인자가 섞였습니다.")
    ;

    private int code;
    private String status;
    private String message;
}
