package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import com.km.projects.PostAndComments.comment.CommentRepository;
import com.km.projects.PostAndComments.post.exception.PostNotFoundException;
import com.km.projects.PostAndComments.post.mapper.PostDto;
import com.km.projects.PostAndComments.post.mapper.PostDtoMapper;
import com.km.projects.PostAndComments.post.request.CreatePostRequest;
import com.km.projects.PostAndComments.post.request.UpdatePostRequest;
import com.km.projects.PostAndComments.post.response.CreatePostResponse;
import com.km.projects.PostAndComments.post.response.UpdatePostResponse;
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

    public Post getSinglePost(Long id) {
        return this.postRepository.findById(id)
                    .orElseThrow(() -> new PostNotFoundException("Post by ID: " + id + " not found."));
    }
    public Post getSinglePostWithComments(Long id) {
        Post post = this.postRepository.findById(id)
                .orElseThrow( () -> new PostNotFoundException("Post by ID: " + id + " not found."));
        PostDto postDto = PostDtoMapper.mapPostToPostDto(post);
        List<Comment> comments = this.commentRepository.findByPostId(id);
        return PostDtoMapper.mapCommentsToPost(postDto,comments);
    }
    public CreatePostResponse createPost(CreatePostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .timestamp(new Date(System.currentTimeMillis()))
                .build();
        return  new CreatePostResponse( this.postRepository.save(post) );
    }

    public UpdatePostResponse updatePost(Long id,UpdatePostRequest request) {
        Post post = this.postRepository.findById(id)
                .orElseThrow( () -> new PostNotFoundException("Post by ID: " + id + " not found."));
                post.setTitle(request.getTitle());
                this.postRepository.flush();
        return  new UpdatePostResponse(post);
    }
}
