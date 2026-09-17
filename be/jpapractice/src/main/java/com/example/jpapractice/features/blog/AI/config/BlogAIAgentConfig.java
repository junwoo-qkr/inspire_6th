package com.example.jpapractice.features.blog.AI.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.jpapractice.features.blog.AI.tools.BlogAITool;

@Configuration 
public class BlogAIAgentConfig {
    
    @Bean
    // Agent 클라이언트가 기본으로 사용하는 ChatClient
    public ChatClient blogChatClient(ChatClient.Builder builder, BlogAITool blogAITool) {
        return builder
            .defaultTools(blogAITool)
            .build();
    }
}
