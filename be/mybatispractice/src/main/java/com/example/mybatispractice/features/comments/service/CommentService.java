package com.example.mybatispractice.features.comments.service;

import org.springframework.stereotype.Service;

import com.example.mybatispractice.features.comments.domain.DTO.CommentRequestDTO;
import com.example.mybatispractice.features.comments.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public int write(CommentRequestDTO request) {
        System.out.println("comment service write");
        // return commentMapper.write(request);
        commentMapper.write(request);
        return request.getId();
    }
}
