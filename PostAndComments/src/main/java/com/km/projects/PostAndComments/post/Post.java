package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_seq")
    @SequenceGenerator(name = "post_seq",sequenceName = "post_seq",allocationSize = 1)
    private Long id;

    private String title;
    private String author;

    @OneToMany(mappedBy = "post")
    @JoinColumn(name = "comment_id")
    private Set<Comment> comments = new HashSet<>();
}
