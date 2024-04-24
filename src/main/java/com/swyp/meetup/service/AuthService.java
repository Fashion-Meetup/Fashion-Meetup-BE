package com.swyp.meetup.service;

import com.swyp.meetup.domain.oauth.dto.LoginResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    LoginResponse kakaoLogin(String code, HttpServletResponse response);

}
