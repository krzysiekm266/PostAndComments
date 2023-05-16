package com.km.projects.PostAndComments.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> allComments = this.commentService.getAllComments();
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }
}
