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
    
    private final static String USER_INSERT = "Успешное добавление";
    
    private final static String USER_NOT_INSERT = "Этот телефон уже есть в addressBook";
    
    private final static String UPDATE = "Обновлено";
    
    private final static String DELETE = "Удалено";

    

    private boolean containsUser(int id) {
        return addressBook.containsKey(id);
    }

    @GetMapping
    public Map<Integer, User> getUsers() {
        return addressBook;
    }

    @GetMapping("{id}")
    public UserAddressBook getUserAddressBook(@PathVariable int id) {
        if (!containsUser(id)) {
            System.out.println(WRONG_PHONE_NUMBER);
        }
        User user = addressBook.get(id);
        UserAddressBook userAddressBook = new UserAddressBook("default",0,0);
        for(Map.Entry<Integer, User> entry : addressBook.entrySet()){
            if(entry.getValue().equals(user)){
               userAddressBook.setAge(user.getAge());
               userAddressBook.setName(user.getName());
               userAddressBook.setPhoneNumber(entry.getKey());
            }
        }
        return userAddressBook;
    }

    @PostMapping("{id}")
    public String addUser(@PathVariable int id, @RequestBody User user) {
        if (containsUser(id)) {
            return USER_NOT_INSERT;
        }
        addressBook.put(id, user);
        return USER_INSERT;
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        if (!containsUser(id)) {
            return WRONG_PHONE_NUMBER;
        }
        addressBook.replace(id, user);
        return UPDATE;
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        if (!containsUser(id)) {
            return WRONG_PHONE_NUMBER;
        }
        addressBook.remove(id);
        return DELETE;
    }

}
