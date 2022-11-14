package com.example.springjpatest.web.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends HttpExceptionImpl {

    private static final long serialVersionUID = 6190938363390639577L;

    public ForbiddenException() {
        super("Access Denied You don't have permission to access.", HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
