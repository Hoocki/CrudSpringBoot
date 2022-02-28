package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;
import com.example.demo.service.AddressBook.AddressBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    @GetMapping
    public Map<Integer, User> getUsers() {
        return addressBookService.getUsers();
    }

    @GetMapping("{phoneNumber}")
    public UserAddressBook getUserAddressBook(@PathVariable int phoneNumber) {
        return addressBookService.getUserAddressBook(phoneNumber);
    }

    @PostMapping("{phoneNumber}")
    public void addUser(@PathVariable int phoneNumber, @RequestBody User user) {
        addressBookService.addUser(phoneNumber, user);
    }

    @PutMapping("{phoneNumber}")
    public void updateUser(@PathVariable int phoneNumber, @RequestBody User user) {
        addressBookService.updateUser(phoneNumber, user);
    }

    @DeleteMapping("{phoneNumber}")
    public void deleteUser(@PathVariable int phoneNumber) {
        addressBookService.deleteUser(phoneNumber);
    }

}
