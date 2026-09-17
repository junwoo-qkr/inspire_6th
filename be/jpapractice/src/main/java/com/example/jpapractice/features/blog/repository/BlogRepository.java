package com.example.jpapractice.features.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpapractice.features.blog.domain.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
    
    // JPQL
    @Query("""
            SELECT b FROM BlogEntity b
            LEFT JOIN FETCH b.comments
            WHERE b.postId = :postId
            """)   
    public Optional<BlogEntity> findByComments(@Param("postId") Integer postId);

    // 본문에 keyword가 포함되고 category가 일치하는 글을 조회
    // 조건에 맞는 BlogEntity로 이루어진 List OR 빈 List 반환
    List<BlogEntity> findByContentContainingIgnoreCaseAndCategory(String keyword, String category);
}
