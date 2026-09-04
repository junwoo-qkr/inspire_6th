package com.example.mybatispractice.features.comments.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatispractice.features.comments.domain.DTO.CommentRequestDTO;
import com.example.mybatispractice.features.comments.domain.DTO.CommentResponseDTO;
import com.example.mybatispractice.features.comments.service.CommentService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/write")
    public ResponseEntity<?> writeComment(@RequestBody CommentRequestDTO request) {
        System.out.println("comment controller write");
        System.out.println("comment controller write param : " + request);
        // int flag = commentService.write(request);
        // return flag == 1
        //     ? ResponseEntity.status(HttpStatus.CREATED).body(blogService.read(request.getPostId()).getComments().get(0))
        //     : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        int id = commentService.write(request);
        return id != 0
            ? ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommentResponseDTO.builder()
                    .postId(request.getPostId())
                    .email(request.getEmail())
                    .comment(request.getComment())
                    .id(id)
                    .build())
            : ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id, @RequestHeader("Authorization") String at) {
        System.out.println("comment controller delete");
        System.out.println("comment controller delete param : " + id);
        int flag = commentService.delete(id);
        return flag == 1
        ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/update/{id}/{comment}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @PathVariable("comment") String comment, @RequestHeader("Authorization") String at) {  // 1번 방법
        System.out.println("comment controller update");
        System.out.println("comment controller delete update : " + id + "\t" + comment);
        
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("comment", comment);

        int flag = commentService.update(map);
        return flag == 1
        ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    // public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Map<String, Object> map, @RequestHeader("Authorization") String at) {  // 2번 방법
}
