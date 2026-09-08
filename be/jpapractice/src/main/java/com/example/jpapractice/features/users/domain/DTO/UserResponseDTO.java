package com.example.jpapractice.features.users.domain.DTO;

import com.example.jpapractice.features.users.domain.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserResponseDTO {
    private String email, password, name, role;

    public static UserResponseDTO fromEntity(UserEntity entity) {
        return UserResponseDTO.builder()
            .email(entity.getEmail())
            .password(entity.getPassword())
            .name(entity.getName())
            .role(entity.getRole())
            .build();
    }
}
