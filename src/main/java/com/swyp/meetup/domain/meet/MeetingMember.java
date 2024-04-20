package com.swyp.meetup.domain.meet;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MeetingMember {
    @EmbeddedId
    private MeetingMemberId id;

}
