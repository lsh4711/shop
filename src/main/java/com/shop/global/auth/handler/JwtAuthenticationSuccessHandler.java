package com.shop.global.auth.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.shop.domain.member.entity.Member;
import com.shop.global.auth.jwt.JwtTokenizer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authResult) throws IOException {
        Member member = (Member)authResult.getPrincipal();
        String accessToken = delegateAccessToken(member);

        response.setHeader("Authorization", "Bearer " + accessToken);

        // -> ### log4j2
        log.error("## 로그인 성공, memberId: %d\n", member.getMemberId());
    }

    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", member.getMemberId());
        claims.put("username", member.getUsername());
        claims.put("roles", member.getRole().getRoles());

        String accessToken = jwtTokenizer.generateAccessToken(claims);

        return accessToken;
    }
}
