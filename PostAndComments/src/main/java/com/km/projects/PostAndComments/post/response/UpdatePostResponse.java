package com.km.projects.PostAndComments.post.response;

import com.km.projects.PostAndComments.post.Post;

import java.util.Date;

public record UpdatePostResponse(Long id, String title, String author, Date timestamp) {
    public UpdatePostResponse(Post post){
        this(
                post.getId(),
                post.getTitle(),
                post.getAuthor(),
                post.getTimestamp()
        );
    }
}
