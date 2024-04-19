package com.swyp.meetup.domain.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swyp.meetup.dto.auth.KakaoTokenResponse;
import com.swyp.meetup.dto.auth.OauthMember;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@NoArgsConstructor
@AllArgsConstructor
@Component("kakaoClient")
public class KakaoClient implements OauthClient{
    @Value("${kakao.client_id}")
    private String clientId;
    @Value("${kakao.redirect_uri}")
    private String redirectUri;
    @Value("${kakao.client_secret}")
    private String clientSecret;


    @Override
    public String getAccessToken(String code) {
        WebClient webClient = WebClient.builder().baseUrl("https://kauth.kakao.com").build();
        MultiValueMap<String, String> forms = new LinkedMultiValueMap<>();
        System.out.println(clientId);
        forms.add("grant_type", "authorization_code");
        forms.add("client_id",clientId);
        forms.add("redirect_uri",redirectUri);
        forms.add("code",code);
        forms.add("client_secret",clientSecret);

        KakaoTokenResponse socialAccessToken = webClient.post()
                .uri("/oauth/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("charset","utf-8")
                .bodyValue(forms)
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .block();

        return socialAccessToken.getAccessToken();
    }

    @Override
    public OauthMember getOauthInfo(String accessToken) {
        WebClient webClient = WebClient.builder().baseUrl("https://kapi.kakao.com").build();
        String str = webClient.post()
                .uri("/v2/user/me")
                .header("Authorization", "Bearer "+accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("charset","utf-8")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String email = "";
        String nickname = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(str);

            nickname = rootNode.path("properties").path("nickname").asText();

            email = rootNode.path("kakao_account").path("email").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new OauthMember(email, nickname);

    }
}
