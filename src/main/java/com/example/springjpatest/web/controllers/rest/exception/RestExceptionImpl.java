package com.example.springjpatest.web.controllers.rest.exception;

import org.springframework.http.HttpStatus;

public abstract class RestExceptionImpl extends RuntimeException implements RestException {

    private static final long serialVersionUID = -1680802726518490152L;
    private HttpStatus httpStatus;

    public RestExceptionImpl(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}