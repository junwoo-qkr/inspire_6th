package com.example.jpapractice.features.users.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpapractice.features.commons.exception.users.LoginFailException;
import com.example.jpapractice.features.commons.token.JwtProvider;
import com.example.jpapractice.features.users.domain.DTO.UserRequestDTO;
import com.example.jpapractice.features.users.domain.DTO.UserResponseDTO;
import com.example.jpapractice.features.users.domain.entity.UserEntity;
import com.example.jpapractice.features.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public UserResponseDTO signUp(UserRequestDTO request) {
        System.out.println("service signUp");
        UserEntity entity = UserRequestDTO.toEntity(request);
        return UserResponseDTO.fromEntity(userRepository.save(entity));

        // 위와 동일한 동작
        // Optional.of(request)
        //     .filter(req -> !userRepository.existsById(request.getEmail()))
        //     .map(req -> userRepository.save(UserRequestDTO.toEntity(request)))
        //     .map(req -> UserResponseDTO.fromEntity(req))
        //     .orElseThrow(() -> new LoginFailException("Sign Up Failed"));
    }

    @Transactional
    public Map<String, Object> signIn(UserRequestDTO request) {
        System.out.println("service signUp");
        UserEntity entity = userRepository
            .findByEmailAndPassword(request.getEmail(), request.getPassword())
            .orElseThrow(() -> new LoginFailException("Sign In Failed"));
        
        // 비밀번호가 hash로 관리될 떼
        // userRepository
        //     .findById(request.getEmail())
        //     .orElseThrow(() -> new LoginFailException("Sign In Failed"));

        System.out.println("Token provider");
        String at = jwtProvider.createAT(request.getEmail());
        String rt = jwtProvider.createRT(request.getEmail());

        Map<String, Object> map = new HashMap<>();
        map.put("response", UserResponseDTO.fromEntity(entity));
        map.put("at", at);
        map.put("rt", rt);

        return map;
    }
}
