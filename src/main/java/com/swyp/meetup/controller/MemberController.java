package com.swyp.meetup.controller;

import com.swyp.meetup.common.api.Api;
import com.swyp.meetup.common.api.ResponseCode;
import com.swyp.meetup.domain.member.MemberCode;
import com.swyp.meetup.domain.member.dto.RestInfo;
import com.swyp.meetup.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/nickname/check")
    public ResponseEntity<?> checkNickname(
            @RequestParam String nickname
    ) {
        boolean existsNickname = memberService.checkNickname(nickname);
        ResponseCode responseCode = (existsNickname)?MemberCode.ALREADY_USE_NICKNAME : MemberCode.NOT_USE_NICKNAME;
        return ResponseEntity.ok()
                .body(Api.response(responseCode));
    }

    @PatchMapping("/members")
    public ResponseEntity<?> updateTextInfo(
            Authentication authentication,
            @RequestBody RestInfo restInfo
    ) {
        Long memberId = Long.valueOf((String) authentication.getPrincipal());
        memberService.updateRestInfo(memberId, restInfo);
        return ResponseEntity.ok()
                .body(Api.response(MemberCode.TEXT_INFO_UPDATE_SUCCESS));
    }
}
