package com.km.projects.PostAndComments.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.km.projects.PostAndComments.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment_post_id")
    private Long postId;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_author")
    private String author;

    @Column(name = " comment_created")
    private Date timestamp ;

}
