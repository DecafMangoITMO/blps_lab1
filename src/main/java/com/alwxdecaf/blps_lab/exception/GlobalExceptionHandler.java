package com.alwxdecaf.blps_lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("Exception: {}", exception);
        return new GlobalExceptionResponse(exception.getFieldError() != null ?
                exception.getFieldError().getDefaultMessage() : "");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse handleRuntimeException(RuntimeException exception) {
        log.warn("Exception: {}", exception);
        return new GlobalExceptionResponse(exception.getMessage());
    }

}
