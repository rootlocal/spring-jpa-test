package com.example.springjpatest.web.controllers;

import com.example.springjpatest.web.exception.HttpException;
import com.example.springjpatest.web.exception.HttpExceptionImpl;
import com.example.springjpatest.web.exception.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingController implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlingController.class);

    // Convert a predefined exception to an HTTP Status code
    @ResponseStatus(value = HttpStatus.CONFLICT,
            reason = "Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        // Nothing to do
    }

    // Specify name of a specific view that will be used to display the error:
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        // Nothing to do.  Returns the logical view name of an error page, passed
        // to the view-resolver(s) in usual way.
        // Note that the exception is NOT available to this view (it is not added
        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
        // below.

        return "databaseError";
    }

    @ExceptionHandler(HttpExceptionImpl.class)
    public ModelAndView handleError(@NotNull HttpServletRequest httpServletRequest,
                                    @NotNull HttpExceptionImpl e) {
        log.error("handleError: {}", e.getMessage());

        String message = e.getMessage();
        StringBuffer url = httpServletRequest.getRequestURL();
        //StackTraceElement[] trace = e.getStackTrace();
        log.error(e.toString());
        ModelAndView modelAndView = new ModelAndView("error");
        //Cookie[] cookies = httpServletRequest.getCookies();

        modelAndView.addObject("message", message);
        modelAndView.addObject("url", url);
        //modelAndView.addObject("cookies", cookies);
        //modelAndView.addObject("trace", trace);

        if (e instanceof HttpException) {
            HttpStatus httpStatus = ((HttpException) e).getHttpStatus();
            modelAndView.addObject("errorCode", httpStatus.value());
            modelAndView.setStatus(httpStatus);
            log.error("httpStatus: {}, url: {}, message: {}", httpStatus, url, message);
        } else {
            log.error("url: {}, message: {}", url, message);
        }

        if (e instanceof NotFoundException) {
            modelAndView.setViewName("404");
        }

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(@NotNull MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @GetMapping(value = {"/404"})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NotFoundPage() {
        return "/404";
    }
}
