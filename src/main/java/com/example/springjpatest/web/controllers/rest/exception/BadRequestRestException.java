package com.example.springjpatest.web.controllers.rest.exception;

import org.springframework.http.HttpStatus;

public class BadRequestRestException extends RestExceptionImpl {

    private static final long serialVersionUID = -8747873547611810738L;

    public BadRequestRestException() {
        super("Not supported. Http Error Code: 400. Bad Request", HttpStatus.BAD_REQUEST);
    }

    public BadRequestRestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestRestException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }


}
