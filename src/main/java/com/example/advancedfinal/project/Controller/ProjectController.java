package com.example.advancedfinal.project.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.advancedfinal.project.entity.Project;
import com.example.advancedfinal.project.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // Filter projects by department, academicYear, techStack
    @GetMapping("/filter")
    public ResponseEntity<List<Project>> filterProjects(
            @RequestParam Optional<String> department,
            @RequestParam Optional<String> academicYear,
            @RequestParam Optional<String> techStack
    ) {
        List<Project> projects = projectService.listProjects(department, academicYear, techStack);
        return ResponseEntity.ok(projects);
    }

    // to get a project using user id
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long id) {
        List<Project> projects = projectService.getProjectsByUserId(id);
        return ResponseEntity.ok(projects);
    }

    // Get project by id
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        Project project = projectService.getProject(id);
        return ResponseEntity.ok(project);
    }

    // Create project with file upload
    @PostMapping(value = "/post/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Project> createProject(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @PathVariable("id") Long userId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("techStack") String techStack
    ) throws IOException {
        Project saved = projectService.createProject(file, userId, title, description, techStack);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update project with optional file update
    @PutMapping(value = "/put/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Project> updateProject(@PathVariable Long id,
                                                 @RequestPart("project") Project project,
                                                 @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Project updated = projectService.updateProject(id, project, file);
        return ResponseEntity.ok(updated);
    }

    // Delete project
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // Download project file by filename
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MalformedURLException {
        Resource resource = projectService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            // Could not determine file type, fallback to default
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Recommend projects based on user's profile (department and academic year) sorted by comments count
    @GetMapping("/recommend/{userId}")
    public ResponseEntity<List<Project>> recommendProjects(@PathVariable Long userId) {
        List<Project> recommendedProjects = projectService.recommendProjectsForUser(userId);
        return ResponseEntity.ok(recommendedProjects);
    }

    
}


