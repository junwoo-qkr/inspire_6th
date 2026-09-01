package com.example.demo.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.MessageDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


// 액션 수행 후 데이터(json) 반환 - SPA 방식
@RestController
// endPoint: http:// ip : port / context / action
@RequestMapping("/rest")  // context 매핑
public class ReactController {
    @GetMapping("/index")  // action 매핑
    // public MessageDTO getMethodName() {
    //     return MessageDTO.builder().message("test message!").build();
    // }
    // 응답을 ResponseEntity에 담기
    public ResponseEntity<?> getMethodName() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .header("access-token", "Gd4kcH*A219%")
            .body(MessageDTO.builder().message("test message!").build());
    }
    
}
