package com.example.jpapractice.features.blog.domain.DTO;

import com.example.jpapractice.features.blog.domain.entity.BlogEntity;
import com.example.jpapractice.features.users.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequestDTO {

    private String title, content, category, email;

    // 댓글을 반환하지 못함
    public BlogEntity toEntity(UserEntity entity) {
        return BlogEntity.builder()
            .title(this.title)
            .content(this.content)
            .category(this.category)
            .writer(entity)
            .build();
    }
}
