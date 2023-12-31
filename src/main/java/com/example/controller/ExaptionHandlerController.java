package com.example.controller;

import com.example.exaption.AppBadRequestException;
import com.example.exaption.AppMethodNotAllowedException;
import com.example.exaption.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExaptionHandlerController {

    @ExceptionHandler(AppBadRequestException.class)
    public ResponseEntity<String> handler(AppBadRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<String> handler(UnAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(AppMethodNotAllowedException.class)
    public ResponseEntity<String> handler(AppMethodNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handler(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handler(AccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

}
