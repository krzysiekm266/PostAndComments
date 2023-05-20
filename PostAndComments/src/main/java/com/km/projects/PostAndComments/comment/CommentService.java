package com.km.projects.PostAndComments.comment;

import com.km.projects.PostAndComments.post.request.CreateCommentRequest;
import com.km.projects.PostAndComments.post.response.CreateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }
    public CreateCommentResponse createPostComment(Long postId, CreateCommentRequest request) {
        Comment comment = Comment.builder()
                .postId(postId)
                .content(request.getContent())
                .author(request.getAuthor())
                .timestamp(new Date(System.currentTimeMillis()))
                .build();

        return new CreateCommentResponse( this.commentRepository.save(comment) );
    }
}
