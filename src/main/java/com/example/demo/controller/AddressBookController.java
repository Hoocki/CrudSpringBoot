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

    @GetMapping
    public Map<Integer, User> getUsers() {
        return addressBook;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id) {
        if (addressBook.containsKey(id) == false) {
            System.out.println("Неверный ключ");
        }
        return addressBook.get(id);
    }

    @PostMapping("{id}")
    public String addUser(@PathVariable int id, @RequestBody User user) {
        if (addressBook.containsKey(id) == true) {
            return "Этот ключ уже есть в addressBook";
        }
        addressBook.put(id, user);
        return "Успешное добавление";
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        if (addressBook.containsKey(id) == false) {
            return "Неверный ключ";
        }
        addressBook.replace(id, user);
        return "Обновлено";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id){
        if (addressBook.containsKey(id) == false) {
            return "Неверный ключ";
        }
        addressBook.remove(id);
        return "Удалено";
    }

}
