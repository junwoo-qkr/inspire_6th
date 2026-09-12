package com.example.jpapractice.features.common.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RedisService {

    // Redis의 TTL: 초 단위
    private static final long RT_TTL = 60 * 60 * 24 * 7;  // 7일
    private final RedisTemplate<String, Object> redisTemplate;

    // 토큰 저장
    public void saveToken(String email, String rt) {
        System.out.println("RedisService save RT");
        redisTemplate.opsForValue()
            .set("RT: " + email, rt, RT_TTL, TimeUnit.SECONDS);
    }
    
    // 토큰 삭제(TTL이 지나기 전에 삭제하고 싶을 때)
    public void deleteToken(String email) {
        System.out.println("RedisService delete RT");
        redisTemplate.delete("RT: " + email);
    }
}
