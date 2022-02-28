package com.example.demo.service.AddressBook;

import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;
import java.util.Map;

public interface AddressBookService {

   Map<Integer, User> getUsers();

   UserAddressBook getUserAddressBook(int phoneNumber);

   void addUser(int phoneNumber, User user);

   void updateUser(int phoneNumber, User user);

   void deleteUser(int phoneNumber);
}
