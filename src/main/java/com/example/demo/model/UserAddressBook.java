package com.example.demo.model;

import lombok.Data;

@Data
public class UserAddressBook extends User {

    private int phoneNumber;

    public UserAddressBook(String name, int age, int phoneNumber) {
        super(name, age);
        this.phoneNumber = phoneNumber;
    }
}
