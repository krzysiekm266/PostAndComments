package com.km.projects.PostAndComments.comment.exception;


import com.km.projects.PostAndComments.comment.CommentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {CommentController.class})
public class CommentExceptionsHandler {
    @ExceptionHandler(value = {CommentNotFoundException.class})
    public ResponseEntity<Object> handlerCommentNotFoundException(CommentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
