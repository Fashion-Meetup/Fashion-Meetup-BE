package com.swyp.meetup.common.exception;

import com.swyp.meetup.common.api.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiException extends RuntimeException{
    private ResponseCode responseCode;
}
