package com.swyp.meetup.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationStatus {
    READ("READ"),
    UNREAD("UNREAD");
    private String value;
}
