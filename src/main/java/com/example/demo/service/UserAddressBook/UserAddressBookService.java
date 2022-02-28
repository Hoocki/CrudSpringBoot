package com.example.demo.service.UserAddressBook;

import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;

import java.util.List;

public interface UserAddressBookService {

    List<UserAddressBook> getUsersAddressBooks();

    User getUserAddressBook(int phoneNumber);

    void addUserAddressBook(UserAddressBook userAddressBook);

    void updateUserAddressBook(UserAddressBook userAddressBook);

    void deleteUserAddressBook(int phoneNumber);
}
