package com.swyp.meetup.domain.member;

import com.swyp.meetup.common.api.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberCode implements ResponseCode {
    NOT_FOUND(400,"BAD REQUEST","회원 정보를 찾을 수 없습니다. 다시 시도해주세요"),
    ALREADY_USE_NICKNAME(200,"OK","존재하는 닉네임입니다."),
    NOT_USE_NICKNAME(200,"OK","사용가능한 닉네임입니다."),
    TEXT_INFO_UPDATE_SUCCESS(200,"OK","회원 정보를 성공적으로 수정했습니다.");


    private int code;
    private String status;
    private String message;
}
