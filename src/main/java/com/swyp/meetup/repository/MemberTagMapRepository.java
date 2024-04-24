package com.swyp.meetup.repository;

import com.swyp.meetup.domain.member.MemberTagId;
import com.swyp.meetup.domain.member.MemberTagMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTagMapRepository extends JpaRepository<MemberTagMap, MemberTagId> {
    void deleteAllById_MemberId(Long memberId);
}
