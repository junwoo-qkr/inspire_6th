package com.example.jpapractice.features.openai.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpapractice.features.openai.service.OpenAIService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/openai")
@RequiredArgsConstructor 
public class OpenAIController {

    private final OpenAIService openAIService;

    @PostMapping("/recommend")
    public ResponseEntity<?> recommend (@RequestParam("weather") String weather, @RequestParam("location") String location) {
        System.out.println("OpenAIController recommend");
        System.out.println("params : " + weather + "\t" + location);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(openAIService.recommend2(weather, location));
            // .body(openAIService.recommend(weather, location));
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> quiz (@RequestParam("subject") String subject) {
        System.out.println("OpenAI Controller quiz");
        System.out.println("params : " + subject);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(openAIService.quiz(subject));       
    }
}
