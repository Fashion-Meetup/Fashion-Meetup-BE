package com.swyp.meetup.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Social {
    KAKAO("kakao"),
    NAVER("naver"),
    GOOGLE("google");
    private String type;
}
