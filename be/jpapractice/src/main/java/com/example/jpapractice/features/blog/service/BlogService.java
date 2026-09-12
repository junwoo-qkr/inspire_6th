package com.example.jpapractice.features.blog.service;

import com.example.jpapractice.features.users.repository.UserRepository;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpapractice.features.blog.domain.DTO.BlogRequestDTO;
import com.example.jpapractice.features.blog.domain.DTO.BlogResponseDTO;
import com.example.jpapractice.features.blog.domain.entity.BlogEntity;
import com.example.jpapractice.features.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

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
}
