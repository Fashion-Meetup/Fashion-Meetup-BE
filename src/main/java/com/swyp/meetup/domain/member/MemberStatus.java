package com.swyp.meetup.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberStatus {
    USER("user"),
    DELETED("deleted");
    private String status;
}
