package com.km.projects.PostAndComments.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
//    @Override
//    @Query("select p from Post p left join fetch p.comments where p.id = :id ")
//    Optional<Post> findById(@Param("id") Long id);



}
