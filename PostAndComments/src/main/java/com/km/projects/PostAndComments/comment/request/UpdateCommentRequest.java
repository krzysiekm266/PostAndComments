package com.km.projects.PostAndComments.comment.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCommentRequest {
    @NotBlank(message = "Please enter a valid comment")
    @Size(min = 6,message = "Please enter a valid comment. Min. characters in comment must be : 6")
    private String content;
}
