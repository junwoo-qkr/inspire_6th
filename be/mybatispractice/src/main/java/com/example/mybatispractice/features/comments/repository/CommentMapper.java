package com.example.mybatispractice.features.comments.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatispractice.features.comments.domain.DTO.CommentRequestDTO;
import com.example.mybatispractice.features.comments.domain.DTO.CommentResponseDTO;

@Mapper
public interface CommentMapper {
    
    public List<CommentResponseDTO> findByPostId(Integer postId);
    public int write(CommentRequestDTO request);
}
