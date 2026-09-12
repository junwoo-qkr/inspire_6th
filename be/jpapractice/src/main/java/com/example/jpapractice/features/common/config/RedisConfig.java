package com.example.jpapractice.features.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// 서버 기동 시 Redis와 연결
@Configuration 
public class RedisConfig {
    
    @Bean 
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory rcf) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(rcf);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        
        return template;
    }
}
