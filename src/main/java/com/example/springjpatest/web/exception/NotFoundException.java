package com.example.springjpatest.web.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpExceptionImpl {

    private static final long serialVersionUID = -8571334737629519377L;

    public NotFoundException() {
        super("Resource not found", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}