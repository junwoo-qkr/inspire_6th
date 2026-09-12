package com.example.jpapractice.features.comment.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpapractice.features.comment.domain.DTO.CommentRequestDTO;
import com.example.jpapractice.features.comment.domain.DTO.CommentResponseDTO;
import com.example.jpapractice.features.comment.service.CommentService;

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
    public ResponseEntity<?> write(@RequestBody CommentRequestDTO request) {
        System.out.println("comment controller write");
        System.out.println("comment controller write param : " + request);

        CommentResponseDTO response = commentService.write(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> delete(@PathVariable("commentId") int commentId, @RequestHeader("Authorization") String at) {
        System.out.println("comment controller delete");
        System.out.println("comment controller delete commentId : " + commentId);
        
        int flag = commentService.delete(commentId);
        return flag == 1
        ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/update/{commentId}/{comment}")
    public ResponseEntity<?> update(@PathVariable("commentId") int commentId, @PathVariable("comment") String comment, @RequestHeader("Authorization") String at) {  // 1번 방법
        System.out.println("comment controller update");
        System.out.println("comment controller update commentId : " + commentId + "\t" + comment);
        
        Map<String, Object> map = new HashMap<>();
        map.put("commentId", commentId);
        map.put("comment", comment);

        int flag = commentService.update(map);
        return flag == 1
        ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    // public ResponseEntity<?> update(@PathVariable("commentId") int commentId, @RequestBody Map<String, Object> map, @RequestHeader("Authorization") String at) {  // 2번 방법
}
