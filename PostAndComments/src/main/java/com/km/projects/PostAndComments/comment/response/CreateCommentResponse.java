package com.km.projects.PostAndComments.comment.response;

import com.km.projects.PostAndComments.comment.Comment;
import lombok.*;

import java.util.Date;


public record CreateCommentResponse(Long postId, String content, String author, Date timestamp) {
    public CreateCommentResponse(Comment comment) {
        this(
                comment.getPostId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getTimestamp()
        );
    }
}
