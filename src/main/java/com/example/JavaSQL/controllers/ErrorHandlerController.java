package com.example.JavaSQL.controllers;

import com.example.JavaSQL.DTOs.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorDTO handle(Exception exception) {
        return ErrorDTO.builder().errorMessage(exception.getMessage()).build();
    }
}