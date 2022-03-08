package com.example.demo.service.addressBook;


import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DefaultAddressBookService implements AddressBookService {


    private final Map<Integer, User> addressBook = new HashMap<>(Map.of(
            89203983, new User("Pol", 25),
            89203123, new User("Lor", 32)
    ));

    private final static String WRONG_PHONE_NUMBER = "Неверный телефон";

    private final static String USER_INSERTED = "Успешное добавление";

    private final static String USER_NOT_INSERTED = "Этот телефон уже есть в addressBook";

    private final static String UPDATED = "Обновлено";

    private final static String DELETED = "Удалено";



    private boolean containsUser(int phoneNumber) {
        return addressBook.containsKey(phoneNumber);
    }

    public Map<Integer, User> getUsers() {
        return addressBook;
    }

    public UserAddressBook getUserAddressBook(int phoneNumber) {
        if (!containsUser(phoneNumber)) {
            throw new IllegalArgumentException(WRONG_PHONE_NUMBER);
        }
        User user = addressBook.get(phoneNumber);
        return new UserAddressBook(user.getName(), user.getAge(), phoneNumber);
    }

    public void addUser(int phoneNumber, User user) {
        if (containsUser(phoneNumber)) {
            log.error(USER_NOT_INSERTED);
        }
        addressBook.put(phoneNumber, user);
        log.info(USER_INSERTED);
    }

    public void updateUser(int phoneNumber, User user) {
        if (!containsUser(phoneNumber)) {
            log.error(WRONG_PHONE_NUMBER);
        }
        addressBook.replace(phoneNumber, user);
        log.info(UPDATED);
    }

    public void deleteUser(int phoneNumber) {
        if (!containsUser(phoneNumber)) {
            log.error(WRONG_PHONE_NUMBER);
        }
        addressBook.remove(phoneNumber);
        log.info(DELETED);
    }

}