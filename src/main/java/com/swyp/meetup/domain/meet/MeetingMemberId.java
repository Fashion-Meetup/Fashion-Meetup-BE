package com.swyp.meetup.domain.meet;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MeetingMemberId implements Serializable {
    private Long meetId;
    private Long memberId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingMemberId memberTagId = (MeetingMemberId) o;
        return Objects.equals(meetId, memberTagId.meetId) && Objects.equals(memberId, memberTagId.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetId, memberId);
    }
}
