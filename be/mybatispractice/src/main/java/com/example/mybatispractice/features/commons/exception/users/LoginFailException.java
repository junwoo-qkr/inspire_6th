package com.example.mybatispractice.features.commons.exception.users;

public class LoginFailException extends RuntimeException {
    
    public LoginFailException() {

    }

    public LoginFailException(String message) {
        super(message);
    }
}
