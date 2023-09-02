package com.shop.global.auth.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.shop.global.exception.ExceptionCode;
import com.shop.global.utils.ResponseUtils;

public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException {
        // log.error("# Authentication failed: {}", exception.getMessage());
        // -> ### log4j2
        System.out.printf("### 로그인 실패");

        ResponseUtils.sendResponse(response, ExceptionCode.UNAUTHORIZED);
    }
}
