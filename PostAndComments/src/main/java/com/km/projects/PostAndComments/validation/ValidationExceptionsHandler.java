package com.km.projects.PostAndComments.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ControllerAdvice
public class ValidationExceptionsHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>() ;
        ex.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(
                        ((FieldError)error).getField(),
                        error.getDefaultMessage())
                );

        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
//
//        Map<String, Collection<?>> errors = new HashMap<>() ;
//        errors.put("errors",ex.getConstraintViolations());
//
//        return new  ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
}
