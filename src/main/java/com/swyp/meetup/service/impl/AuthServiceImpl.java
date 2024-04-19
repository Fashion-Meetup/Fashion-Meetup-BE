package com.swyp.meetup.service.impl;

import com.swyp.meetup.domain.member.Member;
import com.swyp.meetup.domain.member.Social;
import com.swyp.meetup.domain.oauth.OauthClient;
import com.swyp.meetup.dto.auth.OauthMember;
import com.swyp.meetup.repository.MemberRepository;
import com.swyp.meetup.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    @Qualifier("kakaoClient")
    private final OauthClient kakaoClient;


    // TODO 하나로 합치기 -> SocialLogin으로 변경
    @Override
    public String kakaoLogin(String code) {
        OauthClient oauthClient = kakaoClient;

        // accesstoken 생성
        String socialAccessToken = oauthClient.getAccessToken(code);
        // OauthMember 생성
        OauthMember oauthMember = oauthClient.getOauthInfo(socialAccessToken);
        // 유저있으면 패스 없으면 save
        Member member = saveOrUpdate(oauthMember, Social.KAKAO);
        return member.getEmail();
    }

    @Transactional
    protected Member saveOrUpdate(OauthMember oauthMember, Social social) {
        Member member = memberRepository.findByEmail(oauthMember.getEmail())
                .orElse(oauthMember.toEntity(social));

        return memberRepository.save(member);
    }


}
