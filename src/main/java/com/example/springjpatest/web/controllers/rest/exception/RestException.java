package com.example.springjpatest.web.controllers.rest.exception;

import org.springframework.http.HttpStatus;

public interface RestException {

    HttpStatus getHttpStatus();

    void setHttpStatus(HttpStatus httpStatus);
}
