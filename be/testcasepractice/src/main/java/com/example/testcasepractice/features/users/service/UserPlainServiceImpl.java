package com.example.testcasepractice.features.users.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.testcasepractice.features.users.domain.DTO.UserRequestDTO;
import com.example.testcasepractice.features.users.domain.DTO.UserResponseDTO;
import com.example.testcasepractice.features.users.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service(value = "plain")
@RequiredArgsConstructor  // 멤버 변수 중 상수만 초기화
public class UserPlainServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserResponseDTO signIn(UserRequestDTO request) {
        System.out.println("plain service signIn");
        return userMapper.login(request)
            .orElseThrow(() -> new RuntimeException("로그인 실패"));
    }
    
    
}
