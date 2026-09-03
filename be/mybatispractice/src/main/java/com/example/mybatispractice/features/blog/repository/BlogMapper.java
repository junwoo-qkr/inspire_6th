package com.example.mybatispractice.features.blog.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatispractice.features.blog.domain.DTO.BlogRequestDTO;
import com.example.mybatispractice.features.blog.domain.DTO.BlogResponseDTO;

@Mapper
public interface BlogMapper {
    
    public List<BlogResponseDTO> findByAll();
    public int save(BlogRequestDTO request);
    public Optional<BlogResponseDTO> findById(Integer postId);

}
