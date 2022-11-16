package com.example.springjpatest.web.controllers.rest;

import com.example.springjpatest.web.controllers.rest.exception.RestExceptionImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class RestAdviceController {

    @ResponseBody
    @ExceptionHandler(RestExceptionImpl.class)
    public ResponseEntity<HashMap<String, String>> handler(@NotNull HttpServletRequest httpServletRequest,
                                                           @NotNull RestExceptionImpl e) {

        String message = e.getMessage();
        HttpStatus httpStatus = e.getHttpStatus();
        StringBuffer url = httpServletRequest.getRequestURL();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("message", message);
        hashMap.put("url", url.toString());
        hashMap.put("code", httpStatus.toString());

        return new ResponseEntity<>(hashMap, httpStatus);
    }
}
