package com.example.AWPRSpring.controller;


import com.example.AWPRSpring.model.User;
import com.example.AWPRSpring.repository.UserRepo;
import com.example.AWPRSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepo repo;
    @Autowired
    private UserService userService;

    public UserController(UserRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody String username) {
        return repo.save(new User(username));
    }

    @GetMapping("/top")
    public List<User> getTopUsers() {
        return userService.getTopScorers();
    }

    @PutMapping("/{id}/score")
    public ResponseEntity<Void> updateUserScore(
            @PathVariable UUID id,
            @RequestParam int score) {
        userService.updateScore(id, score);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<Integer> updateUserScore(
            @PathVariable UUID id) {
        return repo.findById(id)
                .map(user -> ResponseEntity.ok().body(user.getScore()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID uuid) {
        return repo.findById(uuid).orElseThrow();
    }
}


