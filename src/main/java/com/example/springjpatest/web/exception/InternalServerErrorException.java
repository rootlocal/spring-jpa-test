package com.example.springjpatest.web.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpExceptionImpl {

    private static final long serialVersionUID = -4783959683142532612L;

    public InternalServerErrorException() {
        super("Resource not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}