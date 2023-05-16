package com.km.projects.PostAndComments.post.exceptions;

import com.km.projects.PostAndComments.post.PostController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {PostController.class})
public class PostExceptionsHandler {

    @ExceptionHandler(value = {PostNotFoundException.class})
    public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
