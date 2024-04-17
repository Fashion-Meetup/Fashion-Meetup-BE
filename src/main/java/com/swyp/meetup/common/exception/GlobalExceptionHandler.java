package com.swyp.meetup.common.exception;

import com.swyp.meetup.common.api.Api;
import com.swyp.meetup.common.api.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(value = 1000)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> globalException(Exception e) {
        log.error("",e);
        return ResponseEntity.status(500)
                .body(Api.response(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
