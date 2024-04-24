package com.swyp.meetup.domain.member.dto;

import java.util.List;

public record RestInfo(
        String nickname,
        List<Long> styleTagList
) {
}
