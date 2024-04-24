package com.swyp.meetup.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MemberTagMap {
    @EmbeddedId
    private MemberTagId id;

    public static MemberTagMap from(Long memberId, Long styleTagId){
        return new MemberTagMap(new MemberTagId(memberId, styleTagId));
    }

}
