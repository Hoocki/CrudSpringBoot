package com.example.demo.controller;


import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final List<User> users = new ArrayList<>(Arrays.asList(
            new User("Jim", 19),
            new User("Mike", 23),
            new User("Lor", 18)
    ));


    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id) {
        return users.get(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        users.add(user);
    }

    @PutMapping("{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        users.set(id, user);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        if (id >= users.size() || id < 0) {
            return "Неверно введённый id";
        }
        users.remove(id);
        return "Успешное удаление";
    }

}
