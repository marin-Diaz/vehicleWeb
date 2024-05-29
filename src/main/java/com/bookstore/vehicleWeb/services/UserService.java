package com.bookstore.vehicleWeb.services;

import com.bookstore.vehicleWeb.data.User;
import com.bookstore.vehicleWeb.exceptions.UserExceptions;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> save(User user) throws UserExceptions;

    List<User> findByEmail(String email);
}
