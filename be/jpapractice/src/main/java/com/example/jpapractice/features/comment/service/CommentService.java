package com.example.jpapractice.features.comments.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpapractice.features.blog.domain.entity.BlogEntity;
import com.example.jpapractice.features.blog.repository.BlogRepository;
import com.example.jpapractice.features.comment.domain.DTO.CommentRequestDTO;
import com.example.jpapractice.features.comment.domain.DTO.CommentResponseDTO;
import com.example.jpapractice.features.comment.domain.entity.CommentEntity;
import com.example.jpapractice.features.comment.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    @Transactional 
    public CommentResponseDTO write(CommentRequestDTO request) {
        System.out.println("comment service write");

        BlogEntity post = blogRepository.findById(request.getPostId())
            .orElseThrow(() -> new RuntimeException("Post not found, PostId : " + request.getPostId()));
        CommentEntity entity = request.toEntity(post);
        CommentEntity result = commentRepository.save(entity);

        return CommentResponseDTO.fromEntity(result);
    }

    @Transactional 
    public int delete(int id) {
        System.out.println("comment service delete");
        // CommentEntity entity = commentRepository
        //     .findById(id)
        //     .orElseThrow(() -> new RuntimeException("Comment not found, id : " + id));
        // commentRepository.delete(entity);
        commentRepository.deleteById(id);
        return 1;
    }

    @Transactional
    public int update(Map<String, Object> map) {
        System.out.println("comment service update");
        System.out.println(map.get("id") + "\t" + map.get("comment"));

        CommentEntity entity = commentRepository
            .findById((Integer)map.get("id"))
            .orElseThrow(() -> new RuntimeException("Comment not found, id : " + (Integer)map.get("id")));
        
        entity.updateComment((String)(map.get("comment")));
        // commentRepository.save(entity);
        return 1;
    }
}
