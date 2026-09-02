package com.example.mybatispractice.features.commons.token;

import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    
    // access token
    public String createAT(String email) {
        return "Bearer XXXXXX";
    }

    // refresh token
    public String createRT(String email) {
        return "XXXXXXXXX";
    }
}
