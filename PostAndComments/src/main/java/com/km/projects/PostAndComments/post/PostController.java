package com.km.projects.PostAndComments.post;


import com.km.projects.PostAndComments.comment.CommentService;
import com.km.projects.PostAndComments.post.mapper.PostDto;
import com.km.projects.PostAndComments.post.mapper.PostDtoMapper;
import com.km.projects.PostAndComments.post.request.CreateCommentRequest;
import com.km.projects.PostAndComments.post.request.CreatePostRequest;
import com.km.projects.PostAndComments.post.request.UpdatePostRequest;
import com.km.projects.PostAndComments.post.response.CreateCommentResponse;
import com.km.projects.PostAndComments.post.response.CreatePostResponse;
import com.km.projects.PostAndComments.post.response.UpdatePostResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    private PageRequest preparePageRequest(Integer page,Integer size, String direction,String sortBy) {
        page = (page == null || page <= 0) ? 1 : page;
        String[] sortByFields  = sortBy.split(",");
        PageRequest pageRequestSorted = PageRequest.of(
                --page,
                size,
                Sort.Direction.fromString(direction),
                sortByFields
        );
        return pageRequestSorted;
    }
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPostsPaged(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(defaultValue = "timestamp") String sortBy
    ) {
        PageRequest pageRequestSorted = preparePageRequest(pageNumber,pageSize,sortDirection,sortBy);

        return new ResponseEntity<>(this.postService.getAllPostsPaged(pageRequestSorted),HttpStatus.OK);
    }

    @GetMapping("/posts/comments")
    public ResponseEntity<List<Post>> getAllPostsPagedWithComments(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(defaultValue = "timestamp") String sortBy
    ) {
        PageRequest postsPageRequestSorted = preparePageRequest(pageNumber,pageSize,sortDirection,sortBy);
        PageRequest commentsPageRequestSorted =  preparePageRequest(1,100,"asc","timestamp");
        return new ResponseEntity<>(this.postService.getAllPostsPagedWithComments(postsPageRequestSorted,commentsPageRequestSorted), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Long id) {
        Post post = this.postService.getSinglePost(id);
        PostDto postDto = PostDtoMapper.mapPostToPostDto(post);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<Post> getSinglePostWithComments(
            @NotNull(message = "Please enter a valid post id.")
            @Min(value = 1,message = "Please enter a valid post id(min: 1).")
            @PathVariable Long id
    ) {
        Post post = this.postService.getSinglePostWithComments(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Valid CreatePostRequest request) {
        CreatePostResponse response = this.postService.createPost(request);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CreateCommentResponse> createPostComment(
            @NotNull(message = "Please use a  valid post id.")
            @Min(value = 1,message = "Please use a valid post id(min: 1).")
            @PathVariable
            Long id,

            @RequestBody @Valid CreateCommentRequest request
    ) {
        CreateCommentResponse response = this.commentService.createPostComment(id,request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<UpdatePostResponse> updatePost(
            @PathVariable
            Long id,

            @RequestBody @Valid UpdatePostRequest request
    ) {
        UpdatePostResponse response = this.postService.updatePost(id,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> updatePostComment() {
        throw  new RuntimeException("Not implemented yet.");
    }
    @DeleteMapping("/posts/{id}")
    public void deletePost() {
        throw  new RuntimeException("Not implemented yet.");
    }
}
