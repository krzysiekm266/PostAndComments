package com.km.projects.PostAndComments.comment;

import com.km.projects.PostAndComments.comment.exception.CommentNotFoundException;
import com.km.projects.PostAndComments.comment.request.CreateCommentRequest;
import com.km.projects.PostAndComments.comment.request.UpdateCommentRequest;
import com.km.projects.PostAndComments.comment.response.CreateCommentResponse;
import com.km.projects.PostAndComments.comment.response.UpdateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public UpdateCommentResponse updatePostComment(Long postId, Long commentId, UpdateCommentRequest request) {
        Comment comment = this.commentRepository.findByPostId(postId).stream()
                .filter( commentToUpdate -> commentToUpdate.getId() == commentId)
                .findFirst()
                .orElseThrow( () -> new CommentNotFoundException("Comment not found."));

        comment.setContent(request.getContent());
        this.commentRepository.flush();

        return new UpdateCommentResponse(comment);
    }
}
