package com.example.jpapractice.features.blog.domain.DTO;

import java.util.List;

import com.example.jpapractice.features.blog.domain.entity.BlogEntity;
import com.example.jpapractice.features.comment.domain.DTO.CommentResponseDTO;

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

    private Integer postId;
    private String title, content, category, email;
    private List<CommentResponseDTO> comments;

    public static BlogResponseDTO fromEntity(BlogEntity entity) {
        return BlogResponseDTO.builder()
            .postId(entity.getPostId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .category(entity.getCategory())
            .email(entity.getWriter().getEmail())
            .build();
    }

    public static BlogResponseDTO fromEntityWithComments(BlogEntity entity) {
        return BlogResponseDTO.builder()
            .postId(entity.getPostId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .category(entity.getCategory())
            .email(entity.getWriter().getEmail())
            .comments(entity.getComments().stream().map(CommentResponseDTO::fromEntity).toList())
            .build();
    }
}