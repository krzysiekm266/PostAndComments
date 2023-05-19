package com.km.projects.PostAndComments.post;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<List<PostDto>> getAllPostsPaged(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        pageNumber = (pageNumber == null || pageNumber <= 0) ? 1 : pageNumber;

        PageRequest pageRequestSorted = PageRequest.of(
                --pageNumber,
                pageSize,
                PostSort.by(Post::getTitle,sortDirection)
        );
//        switch (sort) {
//            case "DESC" -> pageRequestSorted.getSortOr( Sort.sort(Post.class).by(Post::getTimestamp).descending());
//            case "ASC" -> pageRequestSorted.getSortOr( Sort.sort(Post.class).by(Post::getTimestamp).ascending());
//            default -> pageRequestSorted.getSortOr( Sort.sort(Post.class).by(Post::getId).descending());
//        }
        return new ResponseEntity<>(this.postService.getAllPostsPaged(pageRequestSorted),HttpStatus.OK);
    }

    @GetMapping("/posts/comments")
    public ResponseEntity<List<Post>> getAllPostsPagedWithComments(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String sort
    ) {
        PageRequest pageRequestSorted = PageRequest.of(--pageNumber, pageSize);
        switch (sort) {
            case "DESC" -> pageRequestSorted.getSortOr( Sort.sort(Post.class).by(Post::getTimestamp).descending());
            case "ASC" -> pageRequestSorted.getSortOr( Sort.sort(Post.class).by(Post::getTimestamp).ascending());
            default -> pageRequestSorted.getSortOr( Sort.sort(Post.class).by(Post::getId).descending());
        }
        return new ResponseEntity<>(this.postService.getAllPostsPagedWithComments(pageRequestSorted), HttpStatus.OK);
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
