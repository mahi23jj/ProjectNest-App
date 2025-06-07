package com.example.advancedfinal.commet.entity;

import java.time.LocalDateTime;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.project.entity.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter 
@Setter
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    // getters and setters
}

