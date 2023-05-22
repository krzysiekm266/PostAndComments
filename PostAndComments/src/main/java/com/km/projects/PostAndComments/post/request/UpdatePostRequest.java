package com.km.projects.PostAndComments.post.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePostRequest {
    @NotBlank(message = "Please enter a valid post title.")
    private String title;

}
