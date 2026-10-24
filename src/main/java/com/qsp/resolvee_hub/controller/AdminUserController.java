package com.qsp.resolvee_hub.controller;

import com.qsp.resolvee_hub.dao.UserRepository;
import com.qsp.resolvee_hub.entity.User;
import com.qsp.resolvee_hub.exception.UserNotFoundException;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET USER BY EMAIL
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found: " + email));
    }

    // UPDATE USER BY EMAIL
    @PutMapping("/{email}")
    public User updateUser(
            @PathVariable String email,
            @RequestBody User updatedUser) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found: " + email));

        user.setName(updatedUser.getName());
        user.setPhone(updatedUser.getPhone());

        return userRepository.save(user);
    }

    // DELETE USER BY EMAIL
    @DeleteMapping("/{email}")
    public String deleteUser(@PathVariable String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found: " + email));

        userRepository.delete(user);

        return "User deleted successfully";
    }
}