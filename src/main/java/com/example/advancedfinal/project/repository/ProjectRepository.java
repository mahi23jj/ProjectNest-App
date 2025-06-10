package com.example.advancedfinal.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advancedfinal.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByDepartment(String department);
    List<Project> findByAcademicYear(String academicYear);
    List<Project> findByDepartmentAndAcademicYear(String department, String academicYear);
    List<Project> findByUser_Id(Long userId);

}

