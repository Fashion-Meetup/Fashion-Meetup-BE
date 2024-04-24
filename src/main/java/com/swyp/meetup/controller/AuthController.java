package com.swyp.meetup.controller;

import com.swyp.meetup.common.api.Api;
import com.swyp.meetup.domain.oauth.AuthResponseCode;
import com.swyp.meetup.domain.oauth.dto.LoginResponse;
import com.swyp.meetup.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/login")
public class AuthController {

    private final AuthService authService;
    
    //TODO 추후에 하나로 합치기

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(
            @RequestParam String code,
            HttpServletResponse response
    ) {
        LoginResponse loginResponse = authService.kakaoLogin(code, response);

        System.out.println(loginResponse);

        return ResponseEntity.ok()
                .body(Api.response(AuthResponseCode.LOGIN_SUCCESS, loginResponse));
    }
}
