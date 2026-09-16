package com.example.jpapractice.features.blog.service;

import com.example.jpapractice.features.users.repository.UserRepository;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpapractice.features.blog.domain.DTO.BlogRequestDTO;
import com.example.jpapractice.features.blog.domain.DTO.BlogResponseDTO;
import com.example.jpapractice.features.blog.domain.entity.BlogEntity;
import com.example.jpapractice.features.blog.repository.BlogRepository;
import com.example.jpapractice.features.openai.domain.QuizResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final ChatClient chatClient;

    public List<BlogResponseDTO> list() {
        System.out.println("Blog Service list");
        return blogRepository.findAll().stream()
            .map(BlogResponseDTO::fromEntity)
            .toList();
    }

    public BlogResponseDTO write(BlogRequestDTO request) {
        System.out.println("Blog Service write");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        System.out.println("request.getEmail() : " + request.getEmail());
        System.out.println("SecurityContextHolder email : " + email);

        // return userRepository.findById(request.getEmail())
        return userRepository.findById(email)
            .map(user -> {
                BlogEntity post = blogRepository.save(request.toEntity(user));
                return BlogResponseDTO.fromEntity(post);
            })
            .orElseThrow(() -> new RuntimeException("Blog Service write Failed"));
    }

    @Transactional(readOnly = true)
    public BlogResponseDTO read(Integer postId) {
        System.out.println("Blog Service read");
        return blogRepository.findByComments(postId)
            .map(BlogResponseDTO::fromEntityWithComments)
            .orElseThrow(() -> new RuntimeException("Post not found, postId : " + postId));
    }

    public String contentGenerate(Map<String, Object> map) {
        String result = chatClient
            .prompt()
            .user(
                """
                    너는 블로그 작성 전문가야.
                    주어지는 카테고리와 키워드를 활용해서 차분한 톤의 블로그를 작성해줘.
                    글자수는 500자 이내로 작성해줘.
                    <조건>
                        - 카테고리 : "%s"
                        - 키워드 : "%s"
                    </조건>
                """.formatted(map.get("category"), map.get("keyword")))
            .call()
            .content();

        return result;
    }
}
