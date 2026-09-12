package com.example.jpapractice.features.comment.domain.DTO;

import com.example.jpapractice.features.comment.domain.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {

    private Integer commentId;
    private String comment, email;
    private Integer postId;

    public static CommentResponseDTO fromEntity(CommentEntity entity) {
        return CommentResponseDTO.builder()
            .commentId(entity.getCommentId())
            .comment(entity.getComment())
            .email(entity.getEmail())
            .postId(entity.getPost().getPostId())
            .build();
    }
}
