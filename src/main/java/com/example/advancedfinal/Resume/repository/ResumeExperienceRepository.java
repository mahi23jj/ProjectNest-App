package com.example.advancedfinal.Resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.advancedfinal.Resume.entity.ResumeExperience;

@Repository
public interface ResumeExperienceRepository extends JpaRepository<ResumeExperience, Long> { }
