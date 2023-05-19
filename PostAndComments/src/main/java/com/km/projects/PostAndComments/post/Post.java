package com.km.projects.PostAndComments.post;

import com.km.projects.PostAndComments.comment.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_seq")
    @SequenceGenerator(name = "post_seq",sequenceName = "post_seq",allocationSize = 1)
    private Long id;

    @NotBlank(message = "Please enter valid post title.")
   // @Min(value = 4,message = "Post title must have min.4 characters.")
    private String title;

    @NotBlank(message = "Please enter valid post author.")
    private String author;

    private final Date timestamp = new Date(System.currentTimeMillis());

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId")
    private List<Comment> comments;

    public final long getTimestampMilisec() {
        return this.timestamp != null ? this.timestamp.getTime() : new Date(System.currentTimeMillis()).getTime();
    }
}
