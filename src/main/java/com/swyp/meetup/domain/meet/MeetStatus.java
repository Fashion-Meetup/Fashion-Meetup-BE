package com.swyp.meetup.domain.meet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeetStatus {
    모집중("모집중"),
    조율중("조율중"),
    모집완료("모집완료"),
    종료("종료");
    private String value;
}
