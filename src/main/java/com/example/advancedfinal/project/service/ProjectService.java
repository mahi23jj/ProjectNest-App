package com.example.advancedfinal.project.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.repository.UserRepository;
import com.example.advancedfinal.project.entity.Project;
import com.example.advancedfinal.project.repository.ProjectRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory.", e);
        }
    }


public List<Project> listProjects(Optional<String> department, Optional<String> academicYear, Optional<String> techStack) {
    return projectRepository.findAll().stream()
        .filter(project -> department.map(d -> d.equalsIgnoreCase(project.getDepartment())).orElse(true))
        .filter(project -> academicYear.map(y -> y.equalsIgnoreCase(project.getAcademicYear())).orElse(true))
        .filter(project -> techStack.map(t -> t.equalsIgnoreCase(project.getTechStack())).orElse(true))
        .toList();
}

    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project createProject( MultipartFile file, Long userId,String Title , String Description, String TechStack) throws IOException {

        Project project = new Project ();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        project.setUser(user);
        project.setPostedAt(LocalDate.now());

         if (user.getprofile() != null) {
        project.setAcademicYear(user.getprofile().getAcademicYear());
        project.setDepartment(user.getprofile().getDepartment());
    }

    project.setTitle(Title);
    project.setDescription(Description);
    project.setTechStack(TechStack);

        if (file != null && !file.isEmpty()) {
            String fileName = storeFile(file);
            project.setFileName(fileName);
        }

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject, MultipartFile file) throws IOException {
        Project existing = getProject(id);

        existing.setTitle(updatedProject.getTitle());
        existing.setDescription(updatedProject.getDescription());
        existing.setTechStack(updatedProject.getTechStack());
        existing.setAcademicYear(updatedProject.getAcademicYear());
        existing.setDepartment(updatedProject.getDepartment());
        // existing.setImageurl(updatedProject.getImageurl());

        if (file != null && !file.isEmpty()) {
            String fileName = storeFile(file);
            existing.setFileName(fileName);
        }

        return projectRepository.save(existing);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

       // ➕ New Method: Get Projects by User ID
    public List<Project> getProjectsByUserId(Long userId) {
        return projectRepository.findByUser_Id(userId);
    }

    private String storeFile(MultipartFile file) throws IOException {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = System.currentTimeMillis() + "-" + originalFileName;
        Path targetLocation = fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return resource;
        } else {
            throw new RuntimeException("File not found: " + fileName);
        }
    }

    public List<Project> recommendProjectsForUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (user.getprofile() == null) {
        throw new RuntimeException("User profile not found");
    }

    String dept = user.getprofile().getDepartment();
    String year = user.getprofile().getAcademicYear();

    return projectRepository.findByDepartmentAndAcademicYear(dept, year).stream()
        .sorted((p1, p2) -> Integer.compare(p2.getComments().size(), p1.getComments().size())) // assumes getComments() returns List<Comment>
        .toList();
}

public Resource downloadProjectFile(Long projectId) throws MalformedURLException {
    Project project = getProject(projectId);
    if (project.getFileName() == null) {
        throw new RuntimeException("No file associated with this project.");
    }

    return loadFileAsResource(project.getFileName());
}


}
