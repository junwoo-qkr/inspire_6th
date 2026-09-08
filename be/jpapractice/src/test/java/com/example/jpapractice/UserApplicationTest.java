package com.example.jpapractice;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpapractice.features.users.domain.DTO.UserRequestDTO;
import com.example.jpapractice.features.users.domain.DTO.UserResponseDTO;
import com.example.jpapractice.features.users.domain.entity.UserEntity;
import com.example.jpapractice.features.users.repository.UserRepository;

@SpringBootTest
@Transactional 
public class UserApplicationTest {

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private Environment env;
    
    @Test 
    public void checkEnv() {
        System.out.println("debug >>>> DB Driver : "+env.getProperty("DB_DRIVER"));
        System.out.println("debug >>>> DB Driver : "+env.getProperty("DB_URL"));
        System.out.println("debug >>>> DB Driver : "+env.getProperty("DB_USER"));
        System.out.println("debug >>>> DB Driver : "+env.getProperty("DB_PASSWORD"));
    }

    @Test 
    public void signUp() {
        System.out.println("signUp");
        System.out.println("repository : " + userRepository);

        UserRequestDTO request = UserRequestDTO.builder()
            .email("p40212@gmail.com")
            .password("1234")
            .name("박준우")
            .role("admin")
            .build();

        UserEntity saveEntity = userRepository.save(UserRequestDTO.toEntity(request));
        System.out.println("entity : " + saveEntity);
        System.out.println("entity to response : " + UserResponseDTO.fromEntity(saveEntity));
    }

    @Test
    public void signIn() {
        System.out.println("signIn");
        System.out.println("repository : " + userRepository);

        UserRequestDTO request = UserRequestDTO.builder()
            .email("p40212@gmail.com")
            .password("1234")
            .build();

        Optional<UserEntity> insertEntity = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        System.out.println("entity : " + insertEntity.get());
        System.out.println("entity to response : " + UserResponseDTO.fromEntity(insertEntity.get()));
    }
}
