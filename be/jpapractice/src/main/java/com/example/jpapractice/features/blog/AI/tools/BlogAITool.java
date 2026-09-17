package com.example.jpapractice.features.blog.AI.tools;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.example.jpapractice.features.blog.domain.DTO.BlogResponseDTO;
import com.example.jpapractice.features.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BlogAITool {
    
    private final BlogService blogService;

    @Tool(description = "category와 keyword로 이미 작성된 게시물을 검색한다.")
    public List<BlogResponseDTO> searchBlogKeyword(String category, String keyword) {
        return blogService.searchByKeyword(category, keyword);
    }
}
