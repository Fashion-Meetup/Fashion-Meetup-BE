package com.swyp.meetup.domain.oauth;

import com.swyp.meetup.dto.auth.OauthMember;

public interface OauthClient {
    String getAccessToken(String code);

    OauthMember getOauthInfo(String accessToken);
}
