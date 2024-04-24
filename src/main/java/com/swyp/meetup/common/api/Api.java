package com.swyp.meetup.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Api <T>{
    private ApiResult result;
    private T body;

    public static <T> Api<T> response(ResponseCode code) {
        return Api.<T>builder()
                .result(ApiResult.set(code))
                .body(null)
                .build();
    }

    public static <T> Api<T> response(ResponseCode code, T body) {
        return Api.<T>builder()
                .result(ApiResult.set(code))
                .body(body)
                .build();
    }
}
