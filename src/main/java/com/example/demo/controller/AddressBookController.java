package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.model.UserAddressBook;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    private final Map<Integer, User> addressBook = new HashMap<>(Map.of(
            89203983, new User("Pol", 25),
            89201234, new User("Lor", 32)
    ));
    
    private final static String WRONG_PHONE_NUMBER = "Неверный телефон";
    
    private final static String USER_INSERTED = "Успешное добавление";
    
    private final static String USER_NOT_INSERTED = "Этот телефон уже есть в addressBook";
    
    private final static String UPDATED = "Обновлено";
    
    private final static String DELETED = "Удалено";

    

    private boolean containsUser(int phoneNumber) {
        return addressBook.containsKey(phoneNumber);
    }

    @GetMapping
    public Map<Integer, User> getUsers() {
        return addressBook;
    }

    @GetMapping("{phoneNumber}")
    public UserAddressBook getUserAddressBook(@PathVariable int phoneNumber) {
        if (!containsUser(phoneNumber)) {
            throw new IllegalArgumentException(WRONG_PHONE_NUMBER);
        }
        User user = addressBook.get(phoneNumber);
        return new UserAddressBook(user.getName(), user.getAge(), phoneNumber);
    }

    @PostMapping("{phoneNumber}")
    public String addUser(@PathVariable int phoneNumber, @RequestBody User user) {
        if (containsUser(phoneNumber)) {
            return USER_NOT_INSERTED;
        }
        addressBook.put(phoneNumber, user);
        return USER_INSERTED;
    }

    @PutMapping("{phoneNumber}")
    public String updateUser(@PathVariable int phoneNumber, @RequestBody User user) {
        if (!containsUser(phoneNumber)) {
            return WRONG_PHONE_NUMBER;
        }
        addressBook.replace(phoneNumber, user);
        return UPDATED;
    }

    @DeleteMapping("{phoneNumber}")
    public String deleteUser(@PathVariable int phoneNumber) {
        if (!containsUser(phoneNumber)) {
            return WRONG_PHONE_NUMBER;
        }
        addressBook.remove(phoneNumber);
        return DELETED;
    }

}
