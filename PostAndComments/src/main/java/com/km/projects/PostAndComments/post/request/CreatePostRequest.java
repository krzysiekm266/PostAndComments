package com.km.projects.PostAndComments.post.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePostRequest {

    @NotBlank(message = "Please enter valid post title.")
    private String title;

    @NotBlank(message = "Please enter valid post author.")
    private String author;

}
