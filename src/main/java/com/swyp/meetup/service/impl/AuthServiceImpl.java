package com.swyp.meetup.service.impl;

import com.swyp.meetup.domain.member.Member;
import com.swyp.meetup.domain.member.Social;
import com.swyp.meetup.domain.oauth.OauthClient;
import com.swyp.meetup.domain.oauth.dto.LoginResponse;
import com.swyp.meetup.domain.oauth.dto.OauthMember;
import com.swyp.meetup.jwt.TokenProvider;
import com.swyp.meetup.repository.MemberRepository;
import com.swyp.meetup.service.AuthService;
import com.swyp.meetup.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    @Qualifier("kakaoClient")
    private final OauthClient kakaoClient;


    // TODO 하나로 합치기 -> SocialLogin으로 변경
    @Override
    public LoginResponse kakaoLogin(String code, HttpServletResponse response) {

        OauthClient oauthClient = kakaoClient;
        String socialAccessToken = oauthClient.getAccessToken(code);
        OauthMember oauthMember = oauthClient.getOauthInfo(socialAccessToken);

        Member member = saveOrUpdate(oauthMember, Social.KAKAO, response);
        String accessToken = tokenProvider.issueAccessToken(member);
        return LoginResponse.from(member, accessToken);
    }

    @Transactional
    protected Member saveOrUpdate(OauthMember oauthMember, Social social, HttpServletResponse response) {
        Member member = memberRepository.findByEmail(oauthMember.getEmail())
                .orElse(oauthMember.toEntity(social));

        String refreshToken = tokenProvider.issueRefreshToken(member);
        member.setRefreshToken(refreshToken);
        CookieUtil.addCookie(response,"refreshToken",refreshToken);

        return memberRepository.save(member);
    }


}
