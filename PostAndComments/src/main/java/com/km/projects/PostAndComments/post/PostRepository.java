package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post,Long> {

}
