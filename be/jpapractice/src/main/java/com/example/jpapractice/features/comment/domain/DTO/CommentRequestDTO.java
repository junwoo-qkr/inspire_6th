package com.example.jpapractice.features.comment.domain.DTO;

import javax.xml.stream.events.Comment;

import com.example.jpapractice.features.blog.domain.entity.BlogEntity;
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
public class CommentRequestDTO {

    private String comment, email;
    private Integer postId;
    private Integer id;

    public CommentEntity toEntity(BlogEntity post) {
        return CommentEntity.builder()
            .comment(this.comment)
            .email(this.email)
            .post(post)
            .build();
    }
}
