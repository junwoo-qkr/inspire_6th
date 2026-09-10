package com.example.jpapractice.features.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpapractice.features.comment.domain.entity.CommentEntity;

@Repository 
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    
}
