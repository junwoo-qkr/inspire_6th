package com.example.jpapractice.features.commons.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.jpapractice.features.commons.exception.users.LoginFailException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<?> handlerLoginFail(LoginFailException e) {
        System.out.println("GlobalExceptionHandler handlerLoginFail");
        System.out.println("error message : " + e.getMessage());
        
        ErrorResponse error = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
