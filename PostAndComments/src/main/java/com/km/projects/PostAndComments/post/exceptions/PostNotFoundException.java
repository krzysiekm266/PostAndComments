package com.km.projects.PostAndComments.post.exceptions;

public class PostNotFoundException  extends IllegalStateException {
    public PostNotFoundException(String s) {
        super(s);
    }
}
