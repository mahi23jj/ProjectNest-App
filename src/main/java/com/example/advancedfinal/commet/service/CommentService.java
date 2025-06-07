package com.example.advancedfinal.commet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.advancedfinal.User.entity.User;
import com.example.advancedfinal.User.repository.UserRepository;
import com.example.advancedfinal.commet.entity.Comment;
import com.example.advancedfinal.commet.repository.CommentRepository;
import com.example.advancedfinal.project.entity.Project;
import com.example.advancedfinal.project.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public Comment addComment(Long projectId, Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setProject(project);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByProject(Long projectId) {
        return commentRepository.findByProjectId(projectId);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

