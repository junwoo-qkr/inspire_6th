package com.example.testcasepractice.features.users.domain.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserResponseDTO {
    private String email, password, name;
}
