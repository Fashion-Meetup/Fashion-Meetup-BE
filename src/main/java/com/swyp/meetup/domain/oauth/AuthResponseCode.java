package com.swyp.meetup.domain.oauth;

import com.swyp.meetup.common.api.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthResponseCode implements ResponseCode {
    LOGIN_SUCCESS(200,"OK","로그인 성공");

    private int code;
    private String status;
    private String message;
}
