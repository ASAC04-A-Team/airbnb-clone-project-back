package com.example.airbnbbackend.filter;


import com.example.airbnbbackend.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    /**
     * Filter Chain은 HTTP 요청이 애플리케이션에 도착할 때 Spring Security에 의해 순차적으로 실행되는 일련의 필터들을 의미
     * token이나 userId가 없으면 요청과 응답을 다음 필터로 전달.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String created = jwtProvider.createToken("aaron@naver.com", List.of("USER", "ADMIN"));
        log.info("[Created] : {}", created);
        log.info("[dofilterChain] : token 값 추출 완료");

        log.info("[dofilterChain] : token 값 유효성 체크");
        if (created != null && jwtProvider.validateToken(created)) {
            Authentication authentication = jwtProvider.getAuthentication(created);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("[dofilterChain] userToken 값 유효성 체크 완료 ");
        }

        filterChain.doFilter(request, response); // 다음 대상으로 요청을 전달.
    }

    /**
     * HTTP 요청에서 Bearer 토큰을 추출
     * Bearer 토큰 != JWT(JSON Web Token)
     * Bearer 토큰 형식 : Authorization: <type> <credentials>
     * 일단 Authorization 추출이 안 돼서 넘어감.
     * 현재 Base64로 함. -> Base64와 Bearer 중 어떤 것이 더 좋은지 ?
     */
    /*private String parseBearerToken(HttpServletRequest request) {
        // Bearer 토큰 헤더
        String authorization = request.getHeader("Authorization");
        // Authorization 헤더가 존재하는지 확인하고, 비어있지 않은지 확인.
        boolean hasAuthorization = StringUtils.hasText(authorization);
        boolean isBearer = authorization.startsWith("Bearer ");

        if(!hasAuthorization) return null;
        if(!isBearer) return null;

        // Bearer 토큰을 추출해서 반환.
        return authorization.substring(7);
    }*/

}
