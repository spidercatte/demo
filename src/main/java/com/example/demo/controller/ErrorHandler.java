package com.example.demo.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@RestController
public class ErrorHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage;
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            if ("date".equals(error.getField())) {
                errorMessage = "Invalid date format. Please use the format yyyy-MM-dd.";
                return ResponseEntity.badRequest().body(errorMessage);
            }
        }
        errorMessage = "Validation failed: " + ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(BindException.class) // Handle BindException as a fallback for MethodArgumentNotValidException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBindException(BindException ex) {
        String errorMessage = "Validation failed: " + ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = "Invalid request: invalid format " + ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }
}