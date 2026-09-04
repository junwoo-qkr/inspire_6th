package com.example.mybatispractice.features.comments.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatispractice.features.comments.domain.DTO.CommentRequestDTO;
import com.example.mybatispractice.features.comments.domain.DTO.CommentResponseDTO;

@Mapper
public interface CommentMapper {
    
    public List<CommentResponseDTO> findByPostId(Integer postId);
    public int write(CommentRequestDTO request);
    public int delete(int id);
    public int update(Map<String, Object> map);
}
