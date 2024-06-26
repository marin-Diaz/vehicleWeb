package com.vehicleWeb.repository;

import com.vehicleWeb.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User  findByEmail(String email);

    User findByPhone(String phone);


}


