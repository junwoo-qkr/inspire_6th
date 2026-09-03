package com.example.mybatispractice.features.comments.domain.DTO;

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
    private Integer id;
    private String comment, email;
    private Integer postId;
}
