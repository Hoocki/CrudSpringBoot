package com.example.demo.controller;


import com.example.demo.model.UserAddressBook;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usersAddressBook")
public class UserAddressBookController {

    private final List<UserAddressBook> usersAddressBooks = new ArrayList<>(Arrays.asList(
            new UserAddressBook("Jim", 19, 12),
            new UserAddressBook("Mike", 23, 23),
            new UserAddressBook("Lor", 18, 42)
    ));

    private final static String USER_ADDRESS_BOOK_INSERT = "UserAddressBook insert successfully";

    private final static String USER_ADDRESS_BOOK_WRONG_NUMBER = "UserAddressBook has wrong phoneNumber";

    private final static String USER_ADDRESS_BOOK_UPDATE = "UserAddressBook update successfully";

    private final static String USER_ADDRESS_BOOK_DELETE = "UserAddressBook delete successfully";

    private UserAddressBook containsPhoneNumber(int phoneNumber) {
        for (UserAddressBook userAddressBook : usersAddressBooks) {
            if (userAddressBook.getPhoneNumber() == phoneNumber) {
                return userAddressBook;
            }
        }
        throw new NoSuchElementException("User wasn't found with this phoneNumber");
    }

    @GetMapping
    public List<UserAddressBook> getUsersAddressBooks() {
        return usersAddressBooks;
    }

    @GetMapping("{phoneNumber}")
    public UserAddressBook getUserAddressBook(@PathVariable int phoneNumber) {
        try {
            return containsPhoneNumber(phoneNumber);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping
    public String addUserAddressBook(@RequestBody UserAddressBook userAddressBook) {
        try {
            containsPhoneNumber(userAddressBook.getPhoneNumber());
            return USER_ADDRESS_BOOK_WRONG_NUMBER;
        } catch (NoSuchElementException e) {
            usersAddressBooks.add(userAddressBook);
            return USER_ADDRESS_BOOK_INSERT;
        }

    }

    @PutMapping
    public String updateUserAddressBook(@RequestBody UserAddressBook userAddressBook) {
        try {
            UserAddressBook userAddressBookId = containsPhoneNumber(userAddressBook.getPhoneNumber());
            usersAddressBooks.set(usersAddressBooks.indexOf(userAddressBookId), userAddressBook);
            return USER_ADDRESS_BOOK_UPDATE;
        } catch (NoSuchElementException e) {
            return USER_ADDRESS_BOOK_WRONG_NUMBER;
        }

    }

    @DeleteMapping("{phoneNumber}")
    public String updateUserAddressBook(@PathVariable int phoneNumber) {
        try {
            UserAddressBook userAddressBookId = containsPhoneNumber(phoneNumber);
            usersAddressBooks.remove(usersAddressBooks.indexOf(userAddressBookId));
            return USER_ADDRESS_BOOK_DELETE;
        } catch (NoSuchElementException e) {
            return USER_ADDRESS_BOOK_WRONG_NUMBER;
        }

    }

}
