package com.swyp.meetup.domain.apply;

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
public class ApplyId implements Serializable {
    private Long meetId;
    private Long member_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplyId applyId = (ApplyId) o;
        return Objects.equals(meetId, applyId.meetId) && Objects.equals(member_id, applyId.member_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetId, member_id);
    }
}
