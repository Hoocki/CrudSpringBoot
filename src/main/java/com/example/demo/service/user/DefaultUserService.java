package com.example.demo.service.user;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class DefaultUserService implements UserService{

    private final List<User> users = new ArrayList<>(Arrays.asList(
            new User("Jim", 19),
            new User("Mike", 23),
            new User("Lor", 18)
    ));

    private static final String WRONG_DELETED = "User didn't delete";

    private static final String DELETED = "User deleted";

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(User user, int id) {
        users.set(id, user);
    }

    public void deleteUser(int id) {
        if (id >= users.size() || id < 0) {
            log.error(WRONG_DELETED);
            throw new IllegalArgumentException(WRONG_DELETED);
        }
        users.remove(id);
        log.info(DELETED);
    }

}
