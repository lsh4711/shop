package com.shop.global.exception;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errorResponses = new HashMap<>();
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();

        errors.forEach(
            error -> errorResponses.put(error.getPropertyPath().toString(), error.getMessage()));

        return ResponseEntity.badRequest().body(errorResponses);
    }

    // 유효성 검사 실패 시
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        Map<String, String> errorResponses = new HashMap<>();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        errors.forEach(error -> errorResponses.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errorResponses);
    }

    //Date 형식이 올바르지 않을 때
    @ExceptionHandler
    public ResponseEntity<String> handleDateTimeParseExceptionHandler(DateTimeParseException e) {
        return ResponseEntity.badRequest().body("날짜 형식이 올바르지 않습니다.");
    }

    //잘못된 HTTP 요청이 들어왔을 경우
    // @ExceptionHandler
    // public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
    //     HttpRequestMethodNotSupportedException e) {
    //     return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
    // }

}
