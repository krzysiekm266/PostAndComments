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
    public Page<Post> getAllPostsPaged(int page,int size) {
        return this.postRepository.findAll(PageRequest.of(page,size));
    }

    public List<Post> getAllPostsWithComments() {
        List<Post> allPosts =  this.postRepository.findAll();
        List<Comment> allComments = this.commentRepository.findAll();
        List<Post> allPostWithComments = PostDtoMapper.mapToPostWithComments(allPosts,allComments);
        return allPostWithComments;
    }

    public Post getSinglePost(Long postId) {
        return this.postRepository.findById(postId)
                    .orElseThrow(() -> new PostNotFoundException("Post by ID: " + postId + " not found."));
    }
}