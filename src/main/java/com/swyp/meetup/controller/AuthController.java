package com.swyp.meetup.controller;

import com.swyp.meetup.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/login")
public class AuthController {

    private final AuthService authService;
    
    //TODO 추후에 하나로 합치기

    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam String code) {
        return authService.kakaoLogin(code);
    }
}
