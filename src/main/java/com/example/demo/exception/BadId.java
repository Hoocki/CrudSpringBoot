package com.example.demo.exception;

public class BadId extends Exception{

    private final Long id;

    public BadId(String message, Long id){
        super(message);
        this.id = id;
    }

    public String getMessage(){
        return "User doesn't exist with this id: " + id;
    }

}
