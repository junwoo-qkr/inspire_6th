package com.example.jpapractice.features.common.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    @PostConstruct
    private void init() {
        System.out.println("JwtProvider getSecretKey secret = " + secret);
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String endPoint = request.getRequestURI();
        String method = request.getMethod();

        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return ;
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return ;
        }

        String token = header.substring(7);

        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        
            String email = claims.getSubject();
            System.out.println("JwtAuthenticationFilter token claims email : " + email);

            String role = claims.get("role", String.class);
            System.out.println("JwtAuthenticationFilter token claims role : " + role);

            UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(email,
                null,
                role != null
                    ? java.util.List.of(() -> "ROLE_" + role)
                    : java.util.List.of()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ;
        }

    }
}
