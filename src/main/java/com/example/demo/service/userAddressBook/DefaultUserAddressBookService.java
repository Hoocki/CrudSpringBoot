package com.example.demo.service.userAddressBook;


import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class DefaultUserAddressBookService implements UserAddressBookService{

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
        log.info(USER_ADDRESS_BOOK_WITHOUT_NUMBER);
        throw new NoSuchElementException(USER_ADDRESS_BOOK_WITHOUT_NUMBER);
    }

    public List<UserAddressBook> getUsersAddressBooks() {
        return usersAddressBooks;
    }

    public User getUserAddressBook(int phoneNumber) {
        UserAddressBook userAddressBook = getUserByPhoneNumber(phoneNumber);
        return new User(
                userAddressBook.getName(),
                userAddressBook.getAge()
        );
    }

    public void addUserAddressBook(UserAddressBook userAddressBook) {
        try {
            getUserByPhoneNumber(userAddressBook.getPhoneNumber());
            log.error(USER_ADDRESS_BOOK_WRONG_NUMBER);
        } catch (NoSuchElementException e) {
            usersAddressBooks.add(userAddressBook);
            log.info(USER_ADDRESS_BOOK_INSERTED);
        }

    }

    public void updateUserAddressBook(UserAddressBook userAddressBook) {
        UserAddressBook userAddressBookId = getUserByPhoneNumber(userAddressBook.getPhoneNumber());
        usersAddressBooks.set(usersAddressBooks.indexOf(userAddressBookId), userAddressBook);
        log.info(USER_ADDRESS_BOOK_UPDATED);
    }

    public void deleteUserAddressBook(int phoneNumber) {
        UserAddressBook userAddressBookId = getUserByPhoneNumber(phoneNumber);
        usersAddressBooks.remove(usersAddressBooks.indexOf(userAddressBookId));
        log.info(USER_ADDRESS_BOOK_DELETED);
    }

}