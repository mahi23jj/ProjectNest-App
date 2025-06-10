package com.example.advancedfinal.Resume.repository;

import com.example.advancedfinal.Resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUser_Id(Long userId);

}
