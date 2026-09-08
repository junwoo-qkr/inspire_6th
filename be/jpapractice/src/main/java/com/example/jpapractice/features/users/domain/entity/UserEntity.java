package com.example.jpapractice.features.users.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.jpapractice.features.blog.domain.entity.BlogEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="SPRING_JPA_USER_TBL")

@Builder 
@Getter 
@ToString 
@NoArgsConstructor 
@AllArgsConstructor 
public class UserEntity {

    @Id
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    private String password;
    private String name;

    // optional(인가된 권한: user, admin, ...)
    private String role;

    @OneToMany(mappedBy = "writer", orphanRemoval = false)
    private List<BlogEntity> posts = new ArrayList<>();
}
