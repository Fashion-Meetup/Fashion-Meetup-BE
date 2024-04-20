package com.swyp.meetup.domain.member;

import com.swyp.meetup.domain.member.MemberTagId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MemberTagMap {
    @EmbeddedId
    private MemberTagId id;

}
