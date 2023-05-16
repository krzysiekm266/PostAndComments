package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import com.km.projects.PostAndComments.comment.CommentService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
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
    private final CommentService commentService;
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPostsPaged(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        pageNumber = ( pageNumber != null && pageNumber >= 0 ) ? pageNumber : 0;
        List<Post> allPosts =  this.postService.getAllPostsPaged(pageNumber,pageSize)
                .stream()
                .toList();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/posts-all")

    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> allPosts =  this.postService.getAllPostsWithComments();
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getSinglePost(@PathVariable Long id) {
        Post post = this.postService.getSinglePost(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
}
