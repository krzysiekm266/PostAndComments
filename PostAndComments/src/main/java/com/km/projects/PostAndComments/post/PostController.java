package com.km.projects.PostAndComments.post;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        return (pageNumber != null && pageNumber >= 1)
                ? new ResponseEntity<>(this.postService.getAllPostsPaged(--pageNumber,pageSize),HttpStatus.OK)
                : new ResponseEntity<>(this.postService.getAllPostsWithComments(), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getSinglePost(@PathVariable Long id) {
        Post post = this.postService.getSinglePost(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid Post post) {
        Post createdPost = this.postService.createPost(post);
        PostDto postDto = PostDto.builder()
                .title(post.getTitle())
                .author(post.getAuthor())
                .build();
        return new ResponseEntity<>(postDto,HttpStatus.CREATED);
    }
}
