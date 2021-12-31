package com.example.demo.model;

public class UserAddressBook extends User {

    private int phoneNumber;

    public UserAddressBook(String name, int age, int phoneNumber) {
        super(name, age);
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
