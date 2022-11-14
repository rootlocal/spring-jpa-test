package com.example.springjpatest.web.exception;

import org.springframework.http.HttpStatus;

public interface HttpException {

    HttpStatus getHttpStatus();

    void setHttpStatus(HttpStatus httpStatus);
}
