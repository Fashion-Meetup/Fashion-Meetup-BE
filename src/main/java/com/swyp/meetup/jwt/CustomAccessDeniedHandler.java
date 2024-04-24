package com.swyp.meetup.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swyp.meetup.common.api.Api;
import com.swyp.meetup.common.api.ErrorCode;
import com.swyp.meetup.common.api.ResponseCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseCode responseCode = ErrorCode.FORBIDDEN;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ObjectMapper ob = new ObjectMapper();
        Api<?> apiResponse = Api.response(responseCode);
        response.getWriter().println(ob.writeValueAsString(apiResponse));
    }
}