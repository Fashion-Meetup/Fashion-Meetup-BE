package com.swyp.meetup.domain.oauth.dto;

import com.swyp.meetup.domain.member.Authority;
import com.swyp.meetup.domain.member.Member;
import com.swyp.meetup.domain.member.MemberStatus;
import com.swyp.meetup.domain.member.Social;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OauthMember {
    private String email;
    private String nickname;

    public Member toEntity(Social social){
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .authority(Authority.ROLE_USER)
                .social(social)
                .status(MemberStatus.USER)
                .build();
    }
}
