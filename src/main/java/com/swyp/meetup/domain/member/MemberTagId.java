package com.swyp.meetup.domain.member;

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
public class MemberTagId implements Serializable {
    private Long memberId;
    private Long styleTagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberTagId memberTagId = (MemberTagId) o;
        return Objects.equals(memberId, memberTagId.memberId) && Objects.equals(styleTagId, memberTagId.styleTagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, styleTagId);
    }
}
