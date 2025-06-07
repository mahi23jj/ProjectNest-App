package com.example.advancedfinal.profile.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advancedfinal.profile.entity.profile;;


public interface profilerepository extends JpaRepository<profile, Long> {
    // List<Comment> findByProjectId(Long projectId);
    Optional<Profile> findByUserId(Long userId);


}
