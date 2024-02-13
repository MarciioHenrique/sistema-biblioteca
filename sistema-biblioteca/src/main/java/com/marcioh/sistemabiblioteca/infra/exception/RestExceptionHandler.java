package com.marcioh.sistemabiblioteca.infra.exception;

import com.marcioh.sistemabiblioteca.exception.BadRequestException;
import com.marcioh.sistemabiblioteca.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<RestExceptionPattern> ResourceNotFoundException(ResourceNotFoundException exception) {
        RestExceptionPattern exceptionPattern = new RestExceptionPattern(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionPattern);
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<RestExceptionPattern> BadRequestException(BadRequestException exception) {
        RestExceptionPattern exceptionPattern = new RestExceptionPattern(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionPattern);
    }
}
