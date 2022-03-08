package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class RestExceptionHandler extends Exception {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError illegalArgumentMessage(IllegalArgumentException exception){
        return createApiError(exception);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError noSuchElementMessage(NoSuchElementException exception){
        return createApiError(exception);
    }

    private ApiError createApiError(Exception exception){
        Date date = new Date();
        return new ApiError(exception.getMessage(), date.toString());
    }

}
