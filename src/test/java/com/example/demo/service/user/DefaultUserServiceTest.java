package com.example.demo.service.user;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static com.example.demo.utils.Constants.Sort.SORTING_ASC;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @InjectMocks
    private DefaultUserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void should_getUsers() {
        List<User> mockUsers = new ArrayList<>(List.of(new User(9L, "ad", 34)));
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<User> users = userService.getUsers(SORTING_ASC);
        assertFalse(users.isEmpty());
    }
}