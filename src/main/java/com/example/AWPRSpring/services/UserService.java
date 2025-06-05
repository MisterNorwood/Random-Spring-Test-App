package com.example.AWPRSpring.services;

import com.example.AWPRSpring.model.User;
import com.example.AWPRSpring.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import records.CreateUserRequest;
import records.UserResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public void updateScore(UUID uuid, int newScore) {
        User user = repo.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setScore(newScore);
        repo.save(user);
    }

    public List<User> getTopScorers() {
        return repo.findTop10ByOrderByScoreDesc();
    }

    public Optional<User> getUserByName(String name) {
        return repo.findByName(name);
    }

    public UserResponse toUserResponse(User user){
        return new UserResponse(user.getUuid(), user.getName(), user.getEmail(), user.getAge(), user.getScore(), user.getSex());
    }

    public List<User> getLowestScorers() {
        return repo.findTop10ByOrderByScoreAsc();
    }

    public User createUser(CreateUserRequest req) {
        User userNew = new User(req.name(), req.sex(), req.age(), req.email());
        return repo.save(userNew);
    }
}

