package com.bookstore.vehicleWeb.services.impl;

import com.bookstore.vehicleWeb.data.User;
import com.bookstore.vehicleWeb.exceptions.UserExceptions;
import com.bookstore.vehicleWeb.repository.UserRepository;
import com.bookstore.vehicleWeb.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository ) {
        this.userRepository = userRepository;

    }

    @Override
    public List<User> findAll() {
        return  userRepository.findAll();
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> save(User user) throws UserExceptions {

        List<User> existingUsers = userRepository.findByEmail(user.getEmail());
        if (!existingUsers.isEmpty()) {
            throw new UserExceptions("User already exists");
        }
        try {
            User savedUser = userRepository.save(user);
            return Optional.of(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error saving User: " + e.getMessage(), e);
        }

    }
}

