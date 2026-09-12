package com.example.jpapractice.features.common.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
public class JwtFilter implements Filter {

    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    // 토큰 유무를 판단하지 않을 엔드포인트
    private static final List<String> WHITE_LIST = List.of(
        "/user/**",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    );

    // Path Match를 도와주는 객체
    private final AntPathMatcher matcher = new AntPathMatcher();

    // 엔드포인트가 화이트리스트에 등록됐는지 확인
    public boolean isPath(String path) {
        return WHITE_LIST.stream()
            .anyMatch(pattern -> matcher.match(pattern, path));
    }

    @PostConstruct 
    private void init() {
        System.out.println("JwtProvider getSecretKey secret = " + secret);
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JwtFilter doFilter");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        String endPoint = req.getRequestURI();
        System.out.println("JwtFilter endPoint : " + endPoint);

        String method = req.getMethod();  // Options로 들어옴
        System.out.println("JwtFilter method : " + method);

        if("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            System.out.println("JwtFilter preflight"); 
            // header set : Origin, Method, Header 
            res.setStatus(HttpServletResponse.SC_OK); 
            res.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,PATCH, DELETE, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Refresh-token");
            res.setHeader("Access-Control-Allow-Credentials", "true");

            chain.doFilter(request, response);
            return ;
        }

        // 화이트리스트에 있는 엔드포인트에 접속할 때
        if (isPath(endPoint)) {
            System.out.println("JwtFilter, " + endPoint + "은(는) 토큰 없이 통과");
            chain.doFilter(request, response);
            return ;
        }

        //  화이트리스트에 없는 엔드포인트에 접속할 때 -> 토큰 유효성 검증
        String header = req.getHeader("Authorization");
        System.out.println("JwtFilter header : " + header);

        // token이 존재하지 않을 때
        if( header == null || !header.startsWith("Bearer ")) {
            System.out.println("JwtFilter UNAUTHORIZED"); 
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
            return ;
        }

        // token이 존재할 때
        String token = header.substring(7);
        System.out.println("JwtFilter token exists, token : " + token); 

        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

            String email = claims.getSubject();
            chain.doFilter(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("JwtFilter token validation fail"); 
            return ;
        }
    }
    
}
