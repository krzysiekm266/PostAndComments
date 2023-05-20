package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import com.km.projects.PostAndComments.comment.CommentRepository;
import com.km.projects.PostAndComments.post.exception.PostNotFoundException;
import com.km.projects.PostAndComments.post.mapper.PostDto;
import com.km.projects.PostAndComments.post.mapper.PostDtoMapper;
import com.km.projects.PostAndComments.post.request.CreatePostRequest;
import com.km.projects.PostAndComments.post.response.CreatePostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private  final CommentRepository commentRepository;

    public List<PostDto> getAllPostsPaged(Pageable pageable) {
        Page<Post> pagedPosts = this.postRepository.findAll(pageable);
        return PostDtoMapper.mapPostsToPostDto(pagedPosts.toList());
    }

    public List<Post> getAllPostsPagedWithComments(Pageable postPageable,Pageable commentsPageable) {
        List<Post> allPosts =  this.postRepository.findAll(postPageable)
                .stream().toList();
        List<Comment> allComments = this.commentRepository.findAll(commentsPageable)
                .stream().toList();
        return  PostDtoMapper.mapToPostsWithComments(allPosts,allComments);
    }

    public Post getSinglePostWithComments(Long postId) {
        return this.postRepository.findById(postId)
                    .orElseThrow(() -> new PostNotFoundException("Post by ID: " + postId + " not found."));
    }

    public CreatePostResponse createPost(CreatePostRequest createRequest) {
        Post post = Post.builder()
                .title(createRequest.getTitle())
                .author(createRequest.getAuthor())
                .timestamp(new Date(System.currentTimeMillis()))
                .build();
        return  new CreatePostResponse( this.postRepository.save(post) );
    }
}
