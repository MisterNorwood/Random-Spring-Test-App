package com.example.AWPRSpring.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Setter
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private Sex sex;

    @Column(nullable = false)
    @Setter
    private short age;

    @Column(nullable = false)
    @Setter
    private int score;

    @Column(nullable = false, unique = true)
    @Setter
    private String email;

    public User(String name, Sex sex, short age,  String email) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.email = email;
    }

    public static enum Sex {
        MALE,
        FEMALE,
    }
}
