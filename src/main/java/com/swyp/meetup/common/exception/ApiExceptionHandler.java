package com.swyp.meetup.common.exception;


import com.swyp.meetup.common.api.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(0)
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> apiException(ApiException e) {
        return ResponseEntity.status(e.getResponseCode().getCode())
                .body(Api.response(e.getResponseCode()));
    }
}
