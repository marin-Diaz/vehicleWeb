package com.bookstore.vehicleWeb.controllers;

import com.bookstore.vehicleWeb.data.User;
import com.bookstore.vehicleWeb.exceptions.UserExceptions;
import com.bookstore.vehicleWeb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/users")

public class UserController {

    private  final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> users = userService.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            Optional<User> savedUser = userService.save(user);
            return savedUser.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(500).body(null));
        } catch (UserExceptions e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> findByEmail(@PathVariable String email) {
        try {
            List<User> users = userService.findByEmail(email);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
