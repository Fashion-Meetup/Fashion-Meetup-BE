package com.swyp.meetup.common.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Api <T>{
    private ApiResult result;
    private T body;

    private Api(ApiResult status, T body) {
        this.result = status;
        this.body = body;
    }

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
