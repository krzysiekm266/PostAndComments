package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import com.km.projects.PostAndComments.comment.CommentRepository;
import com.km.projects.PostAndComments.post.exceptions.PostNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private  final CommentRepository commentRepository;

    public List<PostDto> getAllPostsPaged(Pageable pageable) {
        Page<Post> pagedPosts = this.postRepository.findAll(pageable);
        return PostDtoMapper.mapToPosts(pagedPosts.toList());
    }

    public List<Post> getAllPostsPagedWithComments(Pageable pageable) {
        Page<Post> allPosts =  this.postRepository.findAll(pageable);
        List<Comment> allComments = this.commentRepository.findAll();
        return  PostDtoMapper.mapToPostsWithComments(allPosts.toList(),allComments);
    }

    public Post getSinglePost(Long postId) {
        return this.postRepository.findById(postId)
                    .orElseThrow(() -> new PostNotFoundException("Post by ID: " + postId + " not found."));
    }

    public Post createPost(Post post) {
        return this.postRepository.save(post);
    }
}
