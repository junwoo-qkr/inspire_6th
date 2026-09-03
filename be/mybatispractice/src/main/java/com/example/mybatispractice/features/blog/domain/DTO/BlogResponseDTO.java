package com.example.mybatispractice.features.blog.domain.DTO;

import java.util.List;

import com.example.mybatispractice.features.comments.domain.DTO.CommentResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDTO {
    private Integer id;
    private String title, content, category, email;

    private List<CommentResponseDTO> comments;
}