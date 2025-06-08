package com.example.advancedfinal.Resume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.advancedfinal.Resume.entity.Resume;
import com.example.advancedfinal.Resume.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepo;

    public List<Resume> getUserResumes(Long userId) {
        return resumeRepo.findByUserId(userId);
    }

    public Resume getResumeById(Long id) {
        return resumeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
    }

    public Resume saveResume(Resume resume) {
        if (resume.getEducations() != null) {
            resume.getEducations().forEach(e -> e.setResume(resume));
        }
        if (resume.getExperiences() != null) {
            resume.getExperiences().forEach(e -> e.setResume(resume));
        }
        if (resume.getSkills() != null) {
            resume.getSkills().forEach(s -> s.setResume(resume));
        }
        return resumeRepo.save(resume);
    }

    public void deleteResume(Long id) {
        resumeRepo.deleteById(id);
    }
}
