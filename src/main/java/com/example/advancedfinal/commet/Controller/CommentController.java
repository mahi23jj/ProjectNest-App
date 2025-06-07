package com.example.advancedfinal.commet.Controller;

import com.example.advancedfinal.commet.entity.Comment;
import com.example.advancedfinal.commet.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Map;







@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Add comment to project
    @PostMapping("/projects/{projectId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long projectId,
                                              @RequestParam Long userId,
                                              @RequestBody Map<String, String> body) {
        String content = body.get("content");
        Comment comment = commentService.addComment(projectId, userId, content);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // List all comments of a project
    @GetMapping("/projects/{projectId}/comments")
    public List<Comment> getComments(@PathVariable Long projectId) {
        return commentService.getCommentsByProject(projectId);
    }

    // Delete comment by id
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}

