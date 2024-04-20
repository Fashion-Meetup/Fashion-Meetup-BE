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
public class MeetTagId implements Serializable {
    private Long meetId;
    private Long styleTagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetTagId memberTagId = (MeetTagId) o;
        return Objects.equals(meetId, memberTagId.meetId) && Objects.equals(styleTagId, memberTagId.styleTagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetId, styleTagId);
    }
}
