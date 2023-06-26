package com.km.projects.PostAndComments.comment;

import com.km.projects.PostAndComments.comment.request.CreateCommentRequest;
import com.km.projects.PostAndComments.comment.request.UpdateCommentRequest;
import com.km.projects.PostAndComments.comment.response.CreateCommentResponse;
import com.km.projects.PostAndComments.comment.response.UpdateCommentResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CreateCommentResponse> createPostComment(
            @NotNull(message = "Please use a  valid post id.")
            @Min(value = 1,message = "Please use a valid post id(min: 1).")
            @PathVariable Long id,
            @RequestBody @Valid CreateCommentRequest request
    ) {
        CreateCommentResponse response = this.commentService.createPostComment(id,request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updatePostComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequest request
    ) {
        UpdateCommentResponse response = this.commentService.updatePostComment(postId,commentId,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
