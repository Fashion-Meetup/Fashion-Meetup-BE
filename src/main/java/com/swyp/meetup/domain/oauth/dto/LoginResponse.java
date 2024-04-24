package com.swyp.meetup.domain.oauth.dto;

import com.swyp.meetup.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class LoginResponse {
    private Long id;
    private String nickname;
    private String profile;
    private String accessToken;

    public static LoginResponse from(Member member, String accessToken) {
        return LoginResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .profile(null)
                .accessToken(accessToken)
                .build();
    }
}
