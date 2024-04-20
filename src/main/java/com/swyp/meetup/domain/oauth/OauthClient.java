package com.swyp.meetup.domain.oauth;

import com.swyp.meetup.domain.oauth.dto.OauthMember;

public interface OauthClient {
    String getAccessToken(String code);

    OauthMember getOauthInfo(String accessToken);
}
