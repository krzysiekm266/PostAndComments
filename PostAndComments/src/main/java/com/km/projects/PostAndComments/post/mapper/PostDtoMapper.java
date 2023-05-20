package com.km.projects.PostAndComments.post.mapper;

import com.km.projects.PostAndComments.comment.Comment;
import com.km.projects.PostAndComments.post.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDtoMapper {
    public static final List<Post> mapToPostsWithComments(List<Post> posts, List<Comment> comments) {
        return posts.stream()
                .map(post -> mapPostToPostDto(post))
                .map(post -> mapCommentsToPost(post,comments))
                .collect(Collectors.toList());
    }
    public static final List<PostDto> mapPostsToPostDto(List<Post> posts) {
        return posts.stream()
                .map(post -> mapPostToPostDto(post))
                .collect(Collectors.toList());
    }
    private static PostDto mapPostToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor())
                .timestamp(post.getTimestamp())
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
                .timestamp(post.getTimestamp())
                .comments(commentsByPostId)
                .build();
    }
}
