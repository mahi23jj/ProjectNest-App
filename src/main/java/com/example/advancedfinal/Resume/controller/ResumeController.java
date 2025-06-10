package com.example.advancedfinal.Resume.controller;

import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.advancedfinal.Resume.entity.Resume;
import com.example.advancedfinal.Resume.service.ResumePDFService;
import com.example.advancedfinal.Resume.service.ResumeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumePDFService pdfService;

    @GetMapping("/user/{userId}")
    public List<Resume> getUserResumes(@PathVariable Long userId) {
        return resumeService.getUserResumes(userId);
    }

    @GetMapping("/{id}")
    public Resume getResume(@PathVariable Long id) {
        return resumeService.getResumeById(id);
    }

    @PostMapping
    public Resume createOrUpdate(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }

@GetMapping("/{id}/pdf")
public ResponseEntity<byte[]> downloadResumePdf(@PathVariable Long id) {
    try {
        Resume resume = resumeService.getResumeById(id);
        if (resume == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] pdf = pdfService.generateResumePDF(resume);
        if (pdf == null || pdf.length == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("resume-" + id + ".pdf")
                .build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

}
