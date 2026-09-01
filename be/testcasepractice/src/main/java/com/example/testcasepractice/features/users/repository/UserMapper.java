package com.example.testcasepractice.features.users.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.testcasepractice.features.users.domain.DTO.UserRequestDTO;
import com.example.testcasepractice.features.users.domain.DTO.UserResponseDTO;

@Mapper
public interface UserMapper {
    // 메서드의 이름: UserMapper.xml의 키 값
    public int save(UserRequestDTO request);
    public List<UserResponseDTO> findByAll();
    public Optional<UserResponseDTO> login(UserRequestDTO request);
}
