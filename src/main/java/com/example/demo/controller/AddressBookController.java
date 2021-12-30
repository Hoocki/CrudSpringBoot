package com.example.demo.controller;


import com.example.demo.model.User;
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
    private static String WRONG_KEY = "Неверный ключ";
    private static String CORRECT_KEY = "Найден ключ";
    private static String INSERT = "Успешное добавление";
    private static String WRONG_INSERT = "Этот ключ уже есть в addressBook";
    private static String UPDATE = "Обновлено";
    private static String DELETE = "Удалено";
    private static boolean TRUE = true;
    private static boolean FALSE = false;


    private static boolean containsKey(Map addressBook, @PathVariable int id) {
        if (addressBook.containsKey(id) == false) {
            return FALSE;
        }
        return TRUE;
    }

    @GetMapping
    public Map<Integer, User> getUsers() {
        return addressBook;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id) {
        if (containsKey(addressBook, id) == FALSE) {
            System.out.println(WRONG_KEY);
        }
        return addressBook.get(id);
    }

    @PostMapping("{id}")
    public String addUser(@PathVariable int id, @RequestBody User user) {
        if (containsKey(addressBook, id) == TRUE) {
            return WRONG_INSERT;
        }
        addressBook.put(id, user);
        return INSERT;
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        if (containsKey(addressBook, id) == FALSE) {
            return WRONG_KEY;
        }
        addressBook.replace(id, user);
        return UPDATE;
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        if (containsKey(addressBook, id) == FALSE) {
            return WRONG_KEY;
        }
        addressBook.remove(id);
        return DELETE;
    }

}
