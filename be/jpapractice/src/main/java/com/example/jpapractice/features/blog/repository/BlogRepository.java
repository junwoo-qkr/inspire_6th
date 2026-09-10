package com.example.jpapractice.features.blog.repository;

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
}
