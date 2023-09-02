package com.shop.global.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.shop.global.exception.ExceptionCode;
import com.shop.global.utils.ResponseUtils;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException)
        throws IOException, ServletException {
        ExceptionCode exceptionCode = (ExceptionCode)request.getAttribute("exceptionCode");

        ResponseUtils.sendResponse(response, exceptionCode);
    }
}
