package com.example.springjpatest.web.exception;

import org.springframework.http.HttpStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Page")
public abstract class HttpExceptionImpl extends RuntimeException implements HttpException {

    private static final long serialVersionUID = 6988563163907257842L;
    private HttpStatus httpStatus;

    public HttpExceptionImpl(String message, HttpStatus httpStatus) {
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