package com.swyp.meetup.jwt;

import com.swyp.meetup.common.api.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private final TokenProvider tokenProvider;

    String[] noAuthenticationUrlList = {"/actuator","/api/auth"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if(!noAuthenticate(request))
        {
            try {
                String token = parseBearerToken(request);
                // 토큰 검증하기 JWT이므로 인가 서버에 요청하지 않아도됨
                validateToken(token, request);
                // setContext 에 인증객체 저장하기.
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }catch (Exception e) {
            }
            //엑세스 토큰

        }
        filterChain.doFilter(request,response);
    }

    private void validateToken(String token, HttpServletRequest request) {

        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        }
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            request.setAttribute("error", JwtErrorCode.JWT_MALFORMED);
        } catch (ExpiredJwtException e) {
            request.setAttribute("error",JwtErrorCode.JWT_EXPIRED);
        } catch (UnsupportedJwtException e) {
            request.setAttribute("error",JwtErrorCode.JWT_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", JwtErrorCode.JWT_ILLEGAL);
        } catch (Exception e) {
            request.setAttribute("error", ErrorCode.UNAUTHORIZED);
        }
    }

    private String parseBearerToken(HttpServletRequest request){
        try{
            String bearerToken = request.getHeader("Authorization");
            if(bearerToken.startsWith("Bearer")){
                return bearerToken.substring(7);
            }
        }catch (NullPointerException e){
            request.setAttribute("error", "jwt 토큰이 존재하지 않습니다.");
        }
        return "No JWT";
    }

    private boolean noAuthenticate(HttpServletRequest request) {

        String requestUri = request.getRequestURI();

        for(String uri: noAuthenticationUrlList){
            if(requestUri.contains(uri))
                return true;
        }

        return false;
    }
}