package com.example.mybatispractice.features.users.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatispractice.features.users.domain.DTO.UserRequestDTO;
import com.example.mybatispractice.features.users.domain.DTO.UserResponseDTO;

@Mapper
public interface UserMapper {

    public int save(UserRequestDTO request);
    public Optional<UserResponseDTO> signIn(UserRequestDTO request);
    
}
