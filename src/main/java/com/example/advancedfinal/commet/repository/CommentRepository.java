package com.example.advancedfinal.commet.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advancedfinal.commet.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProjectId(Long projectId);
}

