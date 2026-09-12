package com.example.jpapractice.features.common.exception.users;

public class LoginFailException extends RuntimeException {
    
    public LoginFailException() {

    }

    public LoginFailException(String message) {
        super(message);
    }
}
