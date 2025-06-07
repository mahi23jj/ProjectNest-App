package com.example.advancedfinal.sheduleoptimizer.entity;

import java.time.LocalDate;

import com.example.advancedfinal.User.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PersonalSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String courseName;              // 🆕 Added field
    private LocalDate deadline;
    private String progressDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

