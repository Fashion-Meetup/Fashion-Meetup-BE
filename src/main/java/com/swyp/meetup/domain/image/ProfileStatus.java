package com.swyp.meetup.domain.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfileStatus {
    PROFILE("PROFILE"),
    NOT_PROFILE("NOT_PROFILE");
    private String value;
}
