package com.vehicleWeb.services;

import com.bookstore.vehicleWeb.data.User;
import com.bookstore.vehicleWeb.exceptions.UserExceptions;
import com.bookstore.vehicleWeb.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void save() throws UserExceptions {
        User user= new User();
        user.setAddress("barrio ");
        user.setEmail("faffjd");
        user.setName("andrea");
        user.setPhone("32164792");

        Optional<User> opUser = userService.save(user);

        assertTrue(opUser.isPresent());
    }
}