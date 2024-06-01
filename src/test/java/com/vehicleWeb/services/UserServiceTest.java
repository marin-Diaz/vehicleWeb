package com.vehicleWeb.services;

import com.vehicleWeb.data.User;
import com.vehicleWeb.exceptions.UserException;
import com.vehicleWeb.repository.UserRepository;
import com.vehicleWeb.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @SpyBean
    private UserRepository userRepository;

    @Test
    void save() throws UserException {
        User user = new User();
        user.setAddress("barrio");
        user.setEmail("faffjd");
        user.setName("andrea");
        user.setPhone("32164792");

        Optional<User> opUser = userService.save(user);

        assertTrue(opUser.isPresent());
    }

    @Test
    void findAll() throws UserException {
        mockFindAllWithResults();
        List<User> users = userService.findAll();
        assertNotNull(users);
        assertFalse(users.isEmpty(), "The user list should not be empty");
    }

    private void mockFindAllWithResults() {
        User user = new User();
        user.setAddress("barrio");
        user.setEmail("faffjd");
        user.setName("andrea");
        user.setPhone("32164792");
        doReturn(List.of(user)).when(userRepository).findAll();
    }

    @Test
    void findAllError() throws UserException {
        doReturn(Collections.emptyList()).when(userRepository).findAll();

        List<User> users = userService.findAll();

        assertNotNull(users);
        assertTrue(users.isEmpty(), "The user list should be empty");
    }

    @Test
    void findByEmail() throws UserException {
        String email = "faffjd";
        User users = userService.findByEmail(email);
        assertNotNull(users);
        assertFalse(users == null,"The user list should not be empty for the given email");
        assertEquals(email, users.getEmail(), "The email of the first user should match the queried email");
    }
}