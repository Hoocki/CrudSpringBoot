package com.example.demo.controller;


import com.example.demo.model.User;
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

    private final static String USER_ADDRESS_BOOK_INSERTED = "UserAddressBook insert successfully";

    private final static String USER_ADDRESS_BOOK_WRONG_NUMBER = "UserAddressBook has wrong phoneNumber";

    private final static String USER_ADDRESS_BOOK_UPDATED = "UserAddressBook update successfully";

    private final static String USER_ADDRESS_BOOK_DELETED = "UserAddressBook delete successfully";

    private final static String USER_ADDRESS_BOOK_WITHOUT_NUMBER = "User wasn't found with this phoneNumber";

    private UserAddressBook getUserByPhoneNumber(int phoneNumber) {
        for (UserAddressBook userAddressBook : usersAddressBooks) {
            if (userAddressBook.getPhoneNumber() == phoneNumber) {
                return userAddressBook;
            }
        }
        System.out.println(USER_ADDRESS_BOOK_WITHOUT_NUMBER);
        throw new NoSuchElementException(USER_ADDRESS_BOOK_WITHOUT_NUMBER);
    }

    @GetMapping
    public List<UserAddressBook> getUsersAddressBooks() {
        return usersAddressBooks;
    }

    @GetMapping("{phoneNumber}")
    public User getUserAddressBook(@PathVariable int phoneNumber) {
        UserAddressBook userAddressBook = getUserByPhoneNumber(phoneNumber);
        return new User(
                userAddressBook.getName(),
                userAddressBook.getAge()
        );
    }

    @PostMapping
    public String addUserAddressBook(@RequestBody UserAddressBook userAddressBook) {
        try {
            getUserByPhoneNumber(userAddressBook.getPhoneNumber());
            return USER_ADDRESS_BOOK_WRONG_NUMBER;
        } catch (NoSuchElementException e) {
            usersAddressBooks.add(userAddressBook);
            return USER_ADDRESS_BOOK_INSERTED;
        }

    }

    @PutMapping
    public String updateUserAddressBook(@RequestBody UserAddressBook userAddressBook) {

        UserAddressBook userAddressBookId = getUserByPhoneNumber(userAddressBook.getPhoneNumber());
        usersAddressBooks.set(usersAddressBooks.indexOf(userAddressBookId), userAddressBook);
        return USER_ADDRESS_BOOK_UPDATED;
    }

    @DeleteMapping("{phoneNumber}")
    public String deleteUserAddressBook(@PathVariable int phoneNumber) {
        UserAddressBook userAddressBookId = getUserByPhoneNumber(phoneNumber);
        usersAddressBooks.remove(usersAddressBooks.indexOf(userAddressBookId));
        return USER_ADDRESS_BOOK_DELETED;
    }

}
