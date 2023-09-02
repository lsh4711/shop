package com.shop.global.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.shop.global.exception.ExceptionCode;

public class ResponseUtils {
    public static void sendResponse(HttpServletResponse response, HttpStatus status)
        throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(status.getReasonPhrase());
    }

    public static void sendResponse(HttpServletResponse response, ExceptionCode exceptionCode)
        throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(exceptionCode.getStatusCode());
        response.getWriter().write(exceptionCode.getMessage());
    }
}
