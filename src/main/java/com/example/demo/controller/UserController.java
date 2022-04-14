package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final static String SORTING_ASC = "ASC";

    private final static String SORTING_DESC = "DESC";

    @GetMapping
    public List<User> getUsers(@RequestParam String sort) {
        if (sort.equals(SORTING_ASC)) {
            Collections.sort(userService.getUsers());
        } else if (sort.equals(SORTING_DESC)) {
            userService.getUsers().sort(Collections.reverseOrder());
        }
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        userService.updateUser(user, id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}