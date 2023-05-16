package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDtoMapper {
    public static final List<Post> mapToPostWithComments(List<Post> posts,List<Comment> comments) {
        return posts.stream()
                .map(post -> mapToPostDto(post))
                .map(post -> mapCommentsToPost(post,comments))
                .collect(Collectors.toList());
    }
    private static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor())
                .build();
    }
    private static  Post mapCommentsToPost(PostDto post,List<Comment> comments) {
        List<Comment> commentsByPostId = comments.stream()
                .filter(comment -> comment.getPostId() == post.getId())
                .collect(Collectors.toList());

        return Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor())
                .comments(commentsByPostId)
                .build();
    }
}