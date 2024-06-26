package com.vehicleWeb.services;

import com.vehicleWeb.data.User;
import com.vehicleWeb.exceptions.UserException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll() throws UserException;

    Optional<User> save(User user) throws UserException;

    User findByEmail(String email) throws UserException;

    User updateUser(String email, User user) throws UserException;

    User deleteUser(String email) throws UserException;

    User findByPhone(String phone) throws UserException;;

    Long countUsers()throws UserException;;

    boolean isEmailAvailable(String email);


    boolean isPhoneAvailable(String phone);
}
