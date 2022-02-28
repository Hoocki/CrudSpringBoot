package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;
import com.example.demo.service.UserAddressBook.UserAddressBookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/usersAddressBook")
public class UserAddressBookController {

    private final UserAddressBookService userAddressBookService;

    @GetMapping
    public List<UserAddressBook> getUsersAddressBooks() {
        return userAddressBookService.getUsersAddressBooks();
    }

    @GetMapping("{phoneNumber}")
    public User getUserAddressBook(@PathVariable int phoneNumber) {
        return userAddressBookService.getUserAddressBook(phoneNumber);
    }

    @PostMapping
    public void addUserAddressBook(@RequestBody UserAddressBook userAddressBook) {
        userAddressBookService.addUserAddressBook(userAddressBook);
    }

    @PutMapping
    public void updateUserAddressBook(@RequestBody UserAddressBook userAddressBook) {
        userAddressBookService.updateUserAddressBook(userAddressBook);
    }

    @DeleteMapping("{phoneNumber}")
    public void deleteUserAddressBook(@PathVariable int phoneNumber) {
        userAddressBookService.deleteUserAddressBook(phoneNumber);
    }
}
