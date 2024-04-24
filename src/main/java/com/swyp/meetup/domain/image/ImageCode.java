package com.swyp.meetup.domain.image;

import com.swyp.meetup.common.api.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageCode implements ResponseCode {

    PROFILE_IMAGE_ESSENTIAL(400, "BAD REQUEST", "프로필 이미지는 필수 입력 값입니다."),
    NOT_ENOUGH_IMAGE(400, "BAD REQUEST", "패션이미지는 3개 이상 입력해야합니다."),
    NOT_EXISTS_FILE(400, "BAD REQUEST","파일이 존재하지 않습니다."),
    NOT_MATCH_EXTENSION(400,"BAD REQUEST","이미지 외의 다른 파일 업로드는 불가능합니다."),
    SUCCESS_IMAGE_STORED(200,"OK","이미지가 저장되었습니다.")
    ;

    private int code;
    private String status;
    private String message;
}
