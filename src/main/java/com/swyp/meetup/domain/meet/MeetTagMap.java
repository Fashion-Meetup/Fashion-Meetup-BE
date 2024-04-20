package com.swyp.meetup.domain.meet;

import com.swyp.meetup.domain.member.MemberTagId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MeetTagMap {
    @EmbeddedId
    private MeetTagId id;

}
