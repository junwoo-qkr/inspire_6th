package com.example.jpapractice.features.blog.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.jpapractice.features.comment.domain.entity.CommentEntity;
import com.example.jpapractice.features.users.domain.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "SPRING_JPA_POST_TBL")

@Builder 
@Getter 
@ToString 
@NoArgsConstructor 
@AllArgsConstructor 
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment
    private Integer postId;

    private String title;
    
    private String content;
    
    @Column(columnDefinition = "VARCHAR(50) CHECK(CATEGORY IN ('개발', '생활', '취미', '일상'))")
    private String category;

    // 외래키
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    private UserEntity writer;

    // 부모를 삭제해도 자식은 남겨두기
    // cascade 설정: orphanRemoval이 true일 때 자식들을 어떻게 지울지 정하기
    @OneToMany(mappedBy = "post", orphanRemoval = false)
    private List<CommentEntity> comments = new ArrayList<>();
}
