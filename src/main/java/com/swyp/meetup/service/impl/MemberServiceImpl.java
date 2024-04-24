package com.swyp.meetup.service.impl;

import com.swyp.meetup.common.exception.ApiException;
import com.swyp.meetup.domain.member.Member;
import com.swyp.meetup.domain.member.MemberCode;
import com.swyp.meetup.domain.member.MemberTagMap;
import com.swyp.meetup.domain.member.dto.RestInfo;
import com.swyp.meetup.repository.MemberRepository;
import com.swyp.meetup.repository.MemberTagMapRepository;
import com.swyp.meetup.service.ImageService;
import com.swyp.meetup.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberTagMapRepository memberTagMapRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean checkNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional
    @Override
    public void updateRestInfo(Long memberId, RestInfo restInfo) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ApiException(MemberCode.NOT_FOUND));
        member.setNickname(restInfo.nickname());

        memberTagMapRepository.deleteAllById_MemberId(memberId);

        List<MemberTagMap> memberTagMapList = new ArrayList<>();
        for(Long tagId: restInfo.styleTagList()) {
            memberTagMapList.add(MemberTagMap.from(memberId, tagId));
        }

        memberTagMapRepository.saveAll(memberTagMapList);
    }
}
