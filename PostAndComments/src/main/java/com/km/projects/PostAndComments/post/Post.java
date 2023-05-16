package com.km.projects.PostAndComments.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.km.projects.PostAndComments.comment.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_seq")
    @SequenceGenerator(name = "post_seq",sequenceName = "post_seq",allocationSize = 1)
    private Long id;

    private String title;
    private String author;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId")
    private List<Comment> comments;
}
