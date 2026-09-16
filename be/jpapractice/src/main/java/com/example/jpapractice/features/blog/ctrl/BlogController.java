package com.example.jpapractice.features.blog.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpapractice.features.blog.domain.DTO.BlogRequestDTO;
import com.example.jpapractice.features.blog.domain.DTO.BlogResponseDTO;
import com.example.jpapractice.features.blog.service.BlogService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/index")
    public ResponseEntity<?> index() {
        System.out.println("blog controller index");
        List<BlogResponseDTO> list = blogService.list();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BlogRequestDTO request) {
        System.out.println("blog controller write");
        System.out.println("blog controller write param : " + request);
        BlogResponseDTO response = blogService.write(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/read/{postId}")
    public ResponseEntity<?> read(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String at) {
        System.out.println("blog controller read");
        System.out.println("blog controller read param : " + postId);
        System.out.println("blog controller read access token : " + at);

        BlogResponseDTO response = blogService.read(postId);
        System.out.println("blog controller result : " + response);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/ai/agent")
    public ResponseEntity<?> agent(@RequestBody Map<String, Object> map) {
        System.out.println("blog controller agent param :" + map.get("category") + "\t" + map.get("keyword"));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.contentGenerate(map));
    }
    
    
    
    
}
