package com.example.demo.service.user;

import com.example.demo.exception.BadIdException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DefaultUserService implements UserService{

    private static final String DELETED = "User deleted";

    private static final String WRONG_ID = "wrong id";

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(String sort) {
        List<User> users = userRepository.findAll();
        if (sort.equals(Constants.Sort.SORTING_ASC)) {
            Collections.sort(users);
        } else if (sort.equals(Constants.Sort.SORTING_DESC)) {
            users.sort(Collections.reverseOrder());
        }
        return users;
    }

    public User getUser(Long id) {
        Optional<User> defUser = userRepository.findById(id);
        try {
            if (defUser.isPresent()) {
                return defUser.get();
            }
            throw new BadIdException(WRONG_ID, id);
        } catch (BadIdException e){
            log.error(e.getMessage());
        }
        return null;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Long id) throws BadIdException {
        Optional<User> defUser = userRepository.findById(id);
        if (defUser.isPresent()) {
            User update_user = defUser.get();
            update_user.setAge(user.getAge());
            update_user.setName(user.getName());
            userRepository.save(update_user);
        } else {
            throw new BadIdException(WRONG_ID, id);
        }
    }

    public void deleteUser(Long id) throws BadIdException {
        Optional<User> defUser = userRepository.findById(id);
        if (defUser.isPresent()) {
            userRepository.deleteById(id);
            log.info(DELETED);
        } else {
            throw new BadIdException(WRONG_ID, id);
        }
    }
}
