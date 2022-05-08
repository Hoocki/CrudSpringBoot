package com.example.demo.service.user;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers(String sort);

    User getUser(Long id);

    void addUser(User user);

    void updateUser(User user, Long id);

    void deleteUser(Long id);
}
