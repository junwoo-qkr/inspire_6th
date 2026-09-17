package com.example.jpapractice.features.blog.AI.agent;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service 
public class BlogAIAgent {
    
    private final ChatClient blogChatClient;

    public BlogAIAgent(@Qualifier("blogChatClient") ChatClient blogChatClient) {
        this.blogChatClient = blogChatClient;
    }

    public String generate(Map<String, Object> request) {
        Object categoryValue = request.get("category");
        Object keywordValue = request.get("keyword");

        // categoryValue instanceof String category <--> if (categoryValue instanceof String) { String category = (String)categoryValue; } 
        if (!(categoryValue instanceof String category)
            || category.isBlank()
            || !(keywordValue instanceof String keyword)
            || keyword.isBlank()) {
                throw new IllegalArgumentException("category와 keyword는 필수 입력 사항입니다.");
            }

        return blogChatClient
            .prompt()
            .system("""
                당신은 SEO를 고려한 블로그 초안 작성 에이전트입니다.
                
                절차
                1. searchBlogKeyword 도구와 주어진 카테고리, 키워드를 활용해 게시글을 검색합니다.
                2. 검색 결과가 있으면 제목을 명시하고, 해당 글과의 중복 가능성을 안내합니다.
                3. 검색 결과가 없으면 300자 이내의 본문 초안을 작성합니다.

                규칙
                - searchBlogKeyword는 한번만 호출합니다.
                - 과정 또는 결과의 내용은 데이터베이스에 저장하지 않습니다.
                - 마크다운 문법과 백틱(`)을 사용하지 않습니다.
            """)
            .user("카테고리 '%s', 키워드 '%s'로 초안을 준비해줘."
                .formatted(category, keyword))
            .call()
            .content();
    }
}
