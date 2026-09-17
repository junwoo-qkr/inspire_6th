package com.example.jpapractice.features.common.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.jpapractice.features.common.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // hashing을 위한 객체
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS와 관련된 설정
    @Bean 
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Refresh-token"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Spring Security가 제공하는 필터, CORS 설정, 사용자 정의 필터를 연결
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig filterChain");
        // http.build(): 설정을 바탕으로 필터 체인 생성
        http
            .cors(Customizer.withDefaults())  // CorsConfigurationSource를 보안 필터 체인에 적용
            .csrf(csrf -> csrf.disable())  // csrf 보호 기능 비활성화
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/**", "/swagger-ui/**","/v3/api-docs/**", "/openai/**", "/openapi/**").permitAll()  // 언제나 허용할 엔드포인트 패턴
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // OPTIONS 요청일 땐 모든 패턴 허용
                .requestMatchers("/admin/**").hasRole("ADMIN")  // role이 ADMIN일 때 허용할 패턴
                .anyRequest().authenticated()  // 나머지 요청은 토큰 필요
            ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // 한 요청이 끝나고 인증 정보를 정리 -> 다음 요청에서는 인증 정보를 새로 등록
        
        // Spring Security에 필터 등록
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
