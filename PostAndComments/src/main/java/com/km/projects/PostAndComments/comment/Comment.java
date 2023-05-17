package com.km.projects.PostAndComments.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.km.projects.PostAndComments.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq",sequenceName = "comment_seq",allocationSize = 1)
    private Long id;

    private Long postId;
    private String author;
    private String content;

}
