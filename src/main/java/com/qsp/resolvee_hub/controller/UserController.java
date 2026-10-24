package com.qsp.resolvee_hub.controller;

import com.qsp.resolvee_hub.dao.UserRepository;
import com.qsp.resolvee_hub.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET USER BY EMAIL
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    // UPDATE USER BY EMAIL
    @PutMapping("/email/{email}")
    public User updateUser(
            @PathVariable String email,
            @RequestBody User updatedUser) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setName(updatedUser.getName());
        user.setPhone(updatedUser.getPhone());

        return userRepository.save(user);
    }

    // DELETE USER BY EMAIL
    @DeleteMapping("/email/{email}")
    public String deleteUser(@PathVariable String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);

        return "User deleted successfully";
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}