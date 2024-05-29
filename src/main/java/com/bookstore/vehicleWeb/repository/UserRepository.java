package com.bookstore.vehicleWeb.repository;

import com.bookstore.vehicleWeb.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    List<User> findByEmail(String email);
}

