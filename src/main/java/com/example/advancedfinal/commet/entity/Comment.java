package com.example.advancedfinal.commet.entity;

import java.time.LocalDateTime;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.project.entity.Project;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter 
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonBackReference
    private Project project;

    // getters and setters
}

