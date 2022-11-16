package com.example.springjpatest.web.controllers.rest.exception;

import org.springframework.http.HttpStatus;

public class NotFoundRestException extends RestExceptionImpl {

    private static final long serialVersionUID = -2240788859537015372L;

    public NotFoundRestException() {
        super("Resource not found", HttpStatus.NOT_FOUND);
    }

    public NotFoundRestException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}