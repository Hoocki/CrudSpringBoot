package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;
import com.example.demo.service.addressBook.AddressBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    private static final String PHONE_NUMBER = "{phoneNumber}";

    @GetMapping
    public Map<Integer, User> getUsers() {
        return addressBookService.getUsers();
    }

    @GetMapping(PHONE_NUMBER)
    public UserAddressBook getUserAddressBook(@PathVariable int phoneNumber) {
        return addressBookService.getUserAddressBook(phoneNumber);
    }

    @PostMapping(PHONE_NUMBER)
    public void addUser(@PathVariable int phoneNumber, @RequestBody User user) {
        addressBookService.addUser(phoneNumber, user);
    }

    @PutMapping(PHONE_NUMBER)
    public void updateUser(@PathVariable int phoneNumber, @RequestBody User user) {
        addressBookService.updateUser(phoneNumber, user);
    }

    @DeleteMapping(PHONE_NUMBER)
    public void deleteUser(@PathVariable int phoneNumber) {
        addressBookService.deleteUser(phoneNumber);
    }

}
