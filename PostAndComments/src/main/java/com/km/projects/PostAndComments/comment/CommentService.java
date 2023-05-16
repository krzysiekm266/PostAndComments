package com.km.projects.PostAndComments.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }
}
