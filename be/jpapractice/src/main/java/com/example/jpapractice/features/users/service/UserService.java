package com.example.jpapractice.features.users.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpapractice.features.common.exception.users.LoginFailException;
import com.example.jpapractice.features.common.redis.RedisService;
import com.example.jpapractice.features.common.token.JwtProvider;
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
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO signUp(UserRequestDTO request) {
        System.out.println("service signUp");

        UserEntity entity = UserRequestDTO.toEntity(request.toBuilder()
            .password(passwordEncoder.encode(request.getPassword()))
            .build());  // request의 password를 encoding하고 다시 request에 넣기
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
        
        // 비밀번호가 평문일 때
        // UserEntity entity = userRepository
        //     .findByEmailAndPassword(request.getEmail(), request.getPassword())
        //     .orElseThrow(() -> new LoginFailException("Sign In Failed"));
        
        // 비밀번호가 hash로 관리될 때
        UserEntity entity = userRepository
            .findById(request.getEmail())
            .orElseThrow(() -> new LoginFailException("Sign In Failed"));

        if (!passwordEncoder.matches(request.getPassword(), entity.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }
        
        System.out.println("Token provider");
        String at = jwtProvider.createAT(request.getEmail());
        String rt = jwtProvider.createRT(request.getEmail());

        // RT를 redis에 저장
        redisService.saveToken(entity.getEmail(), rt);

        Map<String, Object> map = new HashMap<>();
        map.put("response", UserResponseDTO.fromEntity(entity));
        map.put("at", at);
        map.put("rt", rt);

        return map;
    }

    // SecurityContextHolder를 사용해서 RT를 redis로부터 삭제
    public void signOut() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        redisService.deleteToken(email);
    }
}
