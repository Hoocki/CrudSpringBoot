package com.example.demo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class RestExceptionHandler extends Exception {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError IllegalArgumentMessage(IllegalArgumentException exception){
        Date date = new Date();
        ApiError error = new ApiError(exception.getMessage(), date.toString());
        return error;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError NoSuchElementMessage(NoSuchElementException exception){
        Date date = new Date();
        ApiError error = new ApiError(exception.getMessage(), date.toString());
        return error;
    }

}
