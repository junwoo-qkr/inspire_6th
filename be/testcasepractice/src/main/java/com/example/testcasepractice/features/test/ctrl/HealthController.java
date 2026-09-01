package com.example.testcasepractice.features.test.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping("/alive")
    public String getMethodName() {
        return "I'm alive!";
    }
    
}
