package com.km.projects.PostAndComments.post.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String author;
    private Date timestamp;
}
