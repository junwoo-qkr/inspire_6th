package com.example.jpapractice.features.commons.token;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    // ms 단위로 토큰의 유효 기간 선언
    private final long ACCESS_TOKEN_EXPIRY = 1000L * 60 * 30;  // 30분
    private final long REFRESH_TOKEN_EXPIRY = 1000L * 60 * 60 * 24 * 7;  // 7일

    private Key getSecretKey() {
        System.out.println("JwtProvider getSecretKey secret = " + secret);
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    
    // access token
    public String createAT(String email) {
        System.out.println("JwtProvider createAT");
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRY))
            .signWith(getSecretKey())
            .compact();
    }

    // refresh token
    public String createRT(String email) {
        System.out.println("JwtProvider createRT");
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY))
            .signWith(getSecretKey())
            .compact();
    }

    // AT로부터 subject(서명 주체)를 알아내기(추후 개발)
    public String getUserEmailFromAT(String at) {
        return null;
    }
}
