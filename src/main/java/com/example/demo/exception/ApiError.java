package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiError {

    private final String message;

    private final String date;

}
