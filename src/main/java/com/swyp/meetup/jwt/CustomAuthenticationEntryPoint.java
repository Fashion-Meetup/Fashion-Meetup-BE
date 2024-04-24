package com.swyp.meetup.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swyp.meetup.common.api.Api;
import com.swyp.meetup.common.api.ErrorCode;
import com.swyp.meetup.common.api.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException {
        ResponseCode errorCode = (ResponseCode) request.getAttribute("error");
        if(errorCode == null) errorCode = ErrorCode.UNAUTHORIZED;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper ob = new ObjectMapper();

        Api<?> apiResponse = Api.response(errorCode);
        response.getWriter().println(ob.writeValueAsString(apiResponse));
    }
}