package com.example.springjpatest.web.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpExceptionImpl {

    private static final long serialVersionUID = -4119679312898777638L;

    public BadRequestException() {
        super("Not supported. Http Error Code: 400. Bad Request", HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }


}
