package com.shop.global.auth.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import com.shop.global.auth.handler.JwtAuthenticationFailureHandler;
import com.shop.global.auth.handler.JwtAuthenticationSuccessHandler;
import com.shop.global.auth.jwt.JwtTokenizer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomFilterConfigurer
        extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
    private final JwtTokenizer jwtTokenizer;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager authenticationManager = httpSecurity
                .getSharedObject(AuthenticationManager.class);

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
            authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/members/login");

        jwtAuthenticationFilter.setAuthenticationSuccessHandler(
            new JwtAuthenticationSuccessHandler(jwtTokenizer));

        jwtAuthenticationFilter.setAuthenticationFailureHandler(
            new JwtAuthenticationFailureHandler());

        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer);

        httpSecurity.addFilter(jwtAuthenticationFilter)
                .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
    }
}
