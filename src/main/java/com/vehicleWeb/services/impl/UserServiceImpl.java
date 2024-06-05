package com.vehicleWeb.services.impl;

import com.vehicleWeb.data.User;
import com.vehicleWeb.exceptions.UserException;
import com.vehicleWeb.repository.UserRepository;
import com.vehicleWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public List<User> findAll() throws UserException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserException("No users found");
        }
        return users;
    }

    @Override
    public User findByEmail(String email) throws UserException {
        User usersWithEmail = userRepository.findByEmail(email);
        if (usersWithEmail == null) {
            throw new UserException("Email not found, please check the email address and try again.");
        }
        return usersWithEmail;

    }

    @Override
    public Optional<User> save(User user) throws UserException {

        User existingUsers = userRepository.findByEmail(user.getEmail());
        if (existingUsers != null) {
            throw new UserException("User already exists");
        }
        try {
            User savedUser = userRepository.save(user);
            return Optional.of(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error saving User: " + e.getMessage(), e);
        }

    }

    @Override
    public com.vehicleWeb.data.User updateUser(String email, User user) throws UserException {
        User existingUserOptional = userRepository.findByEmail(email);
        if (existingUserOptional == null) {
            log.info("User not found with email {}", email);
            throw new UserException("User not found with email: " + email);
        }
        User existingUser = existingUserOptional;

        existingUser.setName(user.getName());
        existingUser.setAddress(user.getAddress());
        existingUser.setPhone(user.getPhone());

        try {
            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new UserException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public User deleteUser(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
        else
        {
            log.info("User not found");
        }
        return null;
    }

    @Override
    public User findByPhone(String phone) throws UserException {
        User userWithPhone = userRepository.findByPhone(phone);
        if (userWithPhone == null) {
            throw new UserException("Phone number not found, please check the phone number and try again.");
        }
        return userWithPhone;
    }

    public Long countUsers() throws UserException {
        try {
            return userRepository.count();
        } catch (Exception e) {
            throw new UserException("Error counting users");
        }
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return userRepository.findByEmail(email) == null;
    }

    @Override
    public boolean isPhoneAvailable(String phone) {
        return userRepository.findByPhone(phone) == null;
    }







}

