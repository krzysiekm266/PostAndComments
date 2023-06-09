package com.km.projects.PostAndComments.comment.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCommentRequest {

    @NotBlank(message = "Please enter a valid comment")
    @Size(min = 6,message = "Please enter a valid comment. Min. characters in comment must be : 6")
    private String content;

    @NotBlank(message = "Please enter a valid comment author.")
    private String author;

}
