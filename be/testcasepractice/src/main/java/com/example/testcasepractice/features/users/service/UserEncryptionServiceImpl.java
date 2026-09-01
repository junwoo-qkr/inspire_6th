package com.example.testcasepractice.features.users.service;

import org.springframework.stereotype.Service;

import com.example.testcasepractice.features.users.domain.DTO.UserRequestDTO;
import com.example.testcasepractice.features.users.domain.DTO.UserResponseDTO;

@Service(value = "encryption")
public class UserEncryptionServiceImpl implements UserService {
    @Override
    public UserResponseDTO signIn(UserRequestDTO request) {
        System.out.println("encrypted service signIn");
        return null;
    }
}
