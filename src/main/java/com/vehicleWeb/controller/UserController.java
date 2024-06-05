package com.vehicleWeb.controller;

import com.vehicleWeb.data.User;
import com.vehicleWeb.exceptions.UserException;
import com.vehicleWeb.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    private  final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> users = userService.findAll();
            log.info("Users list found");
            return ResponseEntity.ok(users);
        } catch (UserException e) {
            log.error("Request can't be processed due to error {}",e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<User> save  (@RequestBody User user) {
        try {
            Optional<User> savedUser = userService.save(user);
            return savedUser.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        } catch (UserException e) {
            log.error("User can´t be saved due to error {}",e);
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        try {
            User user = userService.findByEmail(email);
            log.info("User found with email {}",email);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            log.error("User can't be found with email {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @PostMapping("/update/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(email, user);
            return ResponseEntity.ok(updatedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<User> deleteUser(@PathVariable String email) {
        try {
            User deletedUser = userService.deleteUser(email);
            return ResponseEntity.ok(deletedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<User> findByPhone(@PathVariable String phone) {
        try {
            User user = userService.findByPhone(phone);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        try {
            Long totalUsers = userService.countUsers();
            return ResponseEntity.ok(totalUsers);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}


