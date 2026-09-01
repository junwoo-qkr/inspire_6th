package com.example.testcasepractice;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testcasepractice.features.users.domain.DTO.UserRequestDTO;
import com.example.testcasepractice.features.users.domain.DTO.UserResponseDTO;
import com.example.testcasepractice.features.users.repository.UserMapper;

@SpringBootTest
public class UserApplicationTests {
    @Autowired // container에서 관리하던 mapper.java가 주입됨
    private UserMapper userMapper;

    @Test
    public void signUp() {
        System.out.println("usermapper = " + userMapper);
        // given(데이터 준비)
        UserRequestDTO request = UserRequestDTO.builder()
            .email("testEmail2")
            .password("54ds")
            .name("testName2")
            .build();

        // when(실행)
        int flag = userMapper.save(request);

        // then(검증)
        System.out.println("result : " + flag);
        Assertions.assertEquals(1, flag);
    }

    @Test
    public void listUsers() {
        System.out.println("usermapper = " + userMapper);
        List<UserResponseDTO> list = userMapper.findByAll();

        list.stream().forEach(System.out::println);
    }

    @Test
    public void signIn() {
        System.out.println("usermapper = " + userMapper);
        // given
        UserRequestDTO request = UserRequestDTO.builder()
            .email("testEmail2")
            .password("54ds")
            .build();
        
        // when
        Optional<UserResponseDTO> response = userMapper.login(request);

        // then
        Assertions.assertNotNull(response.get());
        Assertions.assertEquals("testEmail2", response.get().getEmail());
    }
}
