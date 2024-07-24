package com.example.crudops.controllers;

import com.example.crudops.model.User;
import com.example.crudops.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RestController("/")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/users")
    public Mono<User> addUser(@RequestBody User user) {
        log.info("Saving user: {}", user);
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        log.info("Fetching all user...");
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable UUID userId) {
        log.info("Fetching user {}...", userId);
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/users/{userId}")
    public Mono<Void> deleteUser(@PathVariable UUID userId) {
        log.info("Deleting user {}...", userId);
        return userRepository.deleteById(userId);
    }

    @DeleteMapping("/users")
    public Mono<Void> deleteAllUser() {
        log.info("Deleting all users...");
        return userRepository.deleteAll();
    }

}
