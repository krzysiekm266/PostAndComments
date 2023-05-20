package com.km.projects.PostAndComments.post.exception;

public class PostNotFoundException  extends IllegalStateException {
    public PostNotFoundException(String s) {
        super(s);
    }
}
