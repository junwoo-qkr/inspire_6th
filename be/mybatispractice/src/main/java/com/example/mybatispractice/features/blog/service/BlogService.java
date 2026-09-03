package com.example.mybatispractice.features.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mybatispractice.features.blog.domain.DTO.BlogRequestDTO;
import com.example.mybatispractice.features.blog.domain.DTO.BlogResponseDTO;
import com.example.mybatispractice.features.blog.repository.BlogMapper;
import com.example.mybatispractice.features.comments.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogMapper blogMapper;
    private final CommentMapper commentMapper;

    public List<BlogResponseDTO> list() {
        System.out.println("Blog Service list");
        return blogMapper.findByAll();
    }

    public int write(BlogRequestDTO request) {
        System.out.println("Blog Service write");
        return blogMapper.save(request);
    }

    @Transactional(readOnly = true)
    public BlogResponseDTO read(Integer postId) {
        System.out.println("Blog Service read");

        BlogResponseDTO post = blogMapper
            .findById(postId)
            .orElseThrow(() -> new RuntimeException("NOT FOUND. postID : " + postId));
        
        // Method 1
        return post.toBuilder()
            .comments(commentMapper.findByPostId(post.getId()))
            .build();

        // Method 2
        // BlogResponseDTO post = blogMapper
        //     .findById(postId)
        //     .map(dto -> BlogResponseDTO.builder()
        //         .id(dto.getId())
        //         .title(dto.getTitle())
        //         .comments(commentMapper.findByPostId(post.getId()))
        //         .build())
        //     .orElseThrow(() -> new RuntimeException("NOT FOUND. postID : " + postId));
        // return post;

        // Not Recommended
        // post.setComments(commentMapper.findByPostId(post.getId()));
        // return post;
    }
}
