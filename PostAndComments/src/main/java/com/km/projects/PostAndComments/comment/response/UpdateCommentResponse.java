package com.km.projects.PostAndComments.comment.response;

import com.km.projects.PostAndComments.comment.Comment;

import java.util.Date;

public record UpdateCommentResponse(Long postId, String content, String author, Date timestamp) {
    public UpdateCommentResponse(Comment comment) {
        this(
                comment.getPostId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getTimestamp()
        );
    }
}
