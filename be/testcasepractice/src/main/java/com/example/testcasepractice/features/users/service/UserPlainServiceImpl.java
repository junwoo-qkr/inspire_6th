package com.example.testcasepractice.features.users.service;

import org.springframework.stereotype.Service;

import com.example.testcasepractice.features.users.domain.DTO.UserRequestDTO;
import com.example.testcasepractice.features.users.domain.DTO.UserResponseDTO;

@Service(value = "plain")
public class UserPlainServiceImpl implements UserService {

    @Override
    public UserResponseDTO signIn(UserRequestDTO request) {
        System.out.println("plain service signIn");
        return null;
    }
    
    
}
