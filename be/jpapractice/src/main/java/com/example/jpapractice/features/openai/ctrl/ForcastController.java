package com.example.jpapractice.features.openai.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpapractice.features.openai.domain.ForcastRequestDTO;
import com.example.jpapractice.features.openai.service.ForcastService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/openapi")
@RequiredArgsConstructor
public class ForcastController {
    
    private final ForcastService forcastService;

    @PostMapping("/fcst")
    public ResponseEntity<?> fcst(@RequestBody ForcastRequestDTO request) {
        System.out.println("forcast controller fcst params : " + request);
        return ResponseEntity.status(HttpStatus.OK).body(forcastService.connection(request));
    }
    
}
