package com.example.AWPRSpring.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@Table(name = "user_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeStamped {
    @Id
    @Column(length = 36, updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Setter
    private int score;

    public User(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
    }
}
