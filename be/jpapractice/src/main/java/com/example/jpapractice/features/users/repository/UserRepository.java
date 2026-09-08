package com.example.jpapractice.features.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpapractice.features.users.domain.entity.UserEntity;

@Repository 
// JpaRepository<UserEntity, String>: 테이블, 기본값
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public Optional<UserEntity> findByEmailAndPassword(String email, String password);
}