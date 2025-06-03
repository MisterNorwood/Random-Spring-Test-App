package com.example.AWPRSpring.repository;

import com.example.AWPRSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    List<User> findTop10ByOrderByScoreDesc();
    List<User> findTop10ByOrderByScoreAsc();
    Optional<User> findByName(String name);
}
