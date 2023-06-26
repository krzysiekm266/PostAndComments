package com.km.projects.PostAndComments.comment.exception;

public class CommentNotFoundException extends  IllegalStateException {
    public CommentNotFoundException(String s) {
        super(s);
    }
}
