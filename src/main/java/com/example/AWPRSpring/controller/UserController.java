package com.example.AWPRSpring.controller;


import com.example.AWPRSpring.model.User;
import com.example.AWPRSpring.repository.UserRepo;
import com.example.AWPRSpring.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import records.CreateUserRequest;
import records.UserResponse;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepo repo;
    private final UserService userService;

    public UserController(UserRepo repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @PostMapping("/register")
    public User createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/top")
    public List<User> getTopUsers() {
        return userService.getTopScorers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserScore(
            @PathVariable UUID id,
            @RequestParam int score) {
        userService.updateScore(id, score);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lookup")
    public UserResponse getUserByIdOrEmail(@RequestParam(required = false) UUID id,
                                           @RequestParam(required = false) String email) {
        User user = null;

        if (id != null) {
            user = repo.findById(id).orElseThrow();
        } else if (email != null) {
            user = repo.findByEmail(email).orElseThrow();
        } else {
            throw new IllegalArgumentException("Either id or email must be provided.");
        }

        return userService.toUserResponse(user);
    }

}