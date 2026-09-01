package com.example.testcasepractice.features.users.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.testcasepractice.features.users.domain.DTO.UserRequestDTO;
import com.example.testcasepractice.features.users.service.UserEncryptionServiceImpl;
import com.example.testcasepractice.features.users.service.UserPlainServiceImpl;
import com.example.testcasepractice.features.users.service.UserService;

import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")
public class UserController {
    // UserPlainServiceImpl 불러오는 방법
    // @Autowired
    // private UserPlainServiceImpl userService;
    // @Autowired
    // private UserService userPlainServiceImpl;
    // @Resource(name = "plain")
    // private UserService userService;

    // UserEncryptionServiceImpl 불러오는 방법
    // @Autowired
    // private UserEncryptionServiceImpl userService;
    // @Autowired
    // private UserService userEncrpytionServiceImpl;
    // @Resource(name = "encryption")
    // private UserService userService;

    // param 방식
    // @GetMapping("/signIn")
    // public ResponseEntity<?> signIn(@RequestParam("email") String email, @RequestParam("password") String password) {
    //     System.out.println("User Controller");
    //     System.out.println(email + "\t" + password);
    //     return null;
    // }

    @Resource(name = "plain")
    private UserService userService;

    // dto 방식
    @GetMapping("/signIn")
    public ResponseEntity<?> signIn(UserRequestDTO request) {
        System.out.println("User Controller");
        System.out.println("params : " + request);
        System.out.println("userService = " + userService);
        return null;
    }

    @
    
}
