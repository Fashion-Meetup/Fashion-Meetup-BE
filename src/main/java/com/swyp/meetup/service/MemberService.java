package com.swyp.meetup.service;

import com.swyp.meetup.domain.member.dto.RestInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {

    boolean checkNickname(String nickname);

    void updateRestInfo(Long memberId, RestInfo restInfo);
}
