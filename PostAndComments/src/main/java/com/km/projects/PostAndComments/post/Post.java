package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @NotBlank(message = "Please enter valid post title.")
    @Size(min = 4,message = "Post title must have min.4 characters.")
    @Column(name = "post_title")
    private String title;

    @NotBlank(message = "Please enter valid post author.")
    @Column(name = "post_author")
    private String author;

    @Column(name = "post_created")
    private Date timestamp ;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "comment_post_id")
    private List<Comment> comments;

}
