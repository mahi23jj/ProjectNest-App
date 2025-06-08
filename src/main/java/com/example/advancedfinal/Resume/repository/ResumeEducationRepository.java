package com.example.advancedfinal.Resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.advancedfinal.Resume.entity.ResumeEducation;

@Repository
public interface ResumeEducationRepository extends JpaRepository<ResumeEducation, Long> { }
