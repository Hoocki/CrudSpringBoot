package com.example.demo.service.user;

import com.example.demo.exception.BadId;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DefaultUserService implements UserService{

    private final static String SORTING_ASC = "ASC";

    private final static String SORTING_DESC = "DESC";

    private static final String DELETED = "User deleted";

    private static final String WRONG_ID = "wrong id";

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(String sort) {
        List<User> users = userRepository.findAll();
        if (sort.equals(SORTING_ASC)) {
            Collections.sort(users);
        } else if (sort.equals(SORTING_DESC)) {
            users.sort(Collections.reverseOrder());
        }
        return users;
    }

    public User getUser(Long id) {
        Optional<User> def_user = userRepository.findById(id);
        try {
            if (def_user.isPresent()) {
                return def_user.get();
            }
            throw new BadId(WRONG_ID, id);
        } catch (BadId e){
            log.error(e.getMessage());
        }
        return null;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Long id) {
        Optional<User> def_user = userRepository.findById(id);
        try {
            if (def_user.isPresent()) {
                User update_user = def_user.get();
                update_user.setAge(user.getAge());
                update_user.setName(user.getName());
                userRepository.save(update_user);
            }
            else {
                throw new BadId(WRONG_ID, id);
            }
        } catch (BadId e) {
            log.error(e.getMessage());
        }
    }

    public void deleteUser(Long id) {
        Optional<User> def_user = userRepository.findById(id);
        try {
            if (def_user.isPresent()) {
                userRepository.deleteById(id);
                log.info(DELETED);
            }
            else {
                throw new BadId(WRONG_ID, id);
            }
        } catch (BadId e) {
            log.error(e.getMessage());
        }

    }

}
