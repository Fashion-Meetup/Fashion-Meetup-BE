package com.swyp.meetup.domain.chatRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatRoomStatus {
    진행("진행"),
    종료("종료");
    private String value;
}
