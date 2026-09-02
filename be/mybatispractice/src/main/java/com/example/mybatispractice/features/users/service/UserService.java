package com.example.mybatispractice.features.users.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.mybatispractice.features.commons.token.JwtProvider;
import com.example.mybatispractice.features.users.domain.DTO.UserRequestDTO;
import com.example.mybatispractice.features.users.domain.DTO.UserResponseDTO;
import com.example.mybatispractice.features.users.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;

    public int signUp(UserRequestDTO request) {
        System.out.println("service signUp");
        return userMapper.save(request);
    }

    public Map<String, Object> signIn(UserRequestDTO request) {
        System.out.println("service signUp");
        UserResponseDTO response = userMapper.signIn(request)
            .orElseThrow(() -> new RuntimeException("로그인 실패"));

        System.out.println("Token provider");
        String at = jwtProvider.createAT(request.getEmail());
        String rt = jwtProvider.createRT(request.getEmail());

        Map<String, Object> map = new HashMap<>();
        map.put("response", response);
        map.put("at", at);
        map.put("rt", rt);

        return map;
    }
}
