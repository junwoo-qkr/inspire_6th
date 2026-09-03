package com.example.mybatispractice.features.comments.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatispractice.features.comments.domain.DTO.CommentRequestDTO;
import com.example.mybatispractice.features.comments.domain.DTO.CommentResponseDTO;
import com.example.mybatispractice.features.comments.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}
