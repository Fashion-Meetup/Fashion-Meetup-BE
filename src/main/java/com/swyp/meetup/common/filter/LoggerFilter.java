package com.swyp.meetup.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);


        filterChain.doFilter(req, res);
        
        //TODO JWT 적용되면 헤더에 Authorization 값도 넣기

        // request log : uri, method, headers, body
        String uri = req.getRequestURI();
        String method = req.getMethod();

        String requestBody = new String(req.getContentAsByteArray());

        log.info(">>>> uri : {} , method : {} , body : {}",uri, method, requestBody);


        // response log : uri, method, headers, body

        String responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<< uri : {} , method : {} , body : {}",uri, method, responseBody);
        res.copyBodyToResponse();
    }
}
