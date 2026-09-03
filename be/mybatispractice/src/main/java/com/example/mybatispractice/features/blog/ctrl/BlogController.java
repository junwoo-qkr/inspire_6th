package com.example.mybatispractice.features.blog.ctrl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mybatispractice.features.blog.domain.DTO.BlogRequestDTO;
import com.example.mybatispractice.features.blog.domain.DTO.BlogResponseDTO;
import com.example.mybatispractice.features.blog.service.BlogService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/index")
    public ResponseEntity<?> index() {
        System.out.println("blog controller index");
        List<BlogResponseDTO> list = blogService.list();
        // return list.isEmpty() 
        //     ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        //     : ResponseEntity.status(HttpStatus.OK).body(list);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BlogRequestDTO request) {
        System.out.println("blog controller write");
        System.out.println("blog controller write param : " + request);
        int flag = blogService.write(request);
        return flag == 1
            ? ResponseEntity.status(HttpStatus.CREATED).build()
            : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/read/{postId}")
    public ResponseEntity<?> read(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String at) {
        System.out.println("blog controller read");
        System.out.println("blog controller read param : " + postId);
        System.out.println("blog controller read access token : " + at);

        BlogResponseDTO response = blogService.read(postId);
        System.out.println("blog controller result : " + response);

        return response != null
            ? ResponseEntity.status(HttpStatus.OK).body(response)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    
    
}
