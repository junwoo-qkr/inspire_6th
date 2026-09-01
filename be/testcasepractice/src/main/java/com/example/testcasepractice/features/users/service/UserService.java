package com.example.testcasepractice.features.users.service;

import com.example.testcasepractice.features.users.domain.DTO.UserRequestDTO;
import com.example.testcasepractice.features.users.domain.DTO.UserResponseDTO;

public interface UserService {
    public UserResponseDTO signIn(UserRequestDTO request);
}
