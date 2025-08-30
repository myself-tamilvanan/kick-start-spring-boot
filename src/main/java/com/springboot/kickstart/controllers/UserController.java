package com.springboot.kickstart.controllers;

import com.springboot.kickstart.entity.UserEntity;
import com.springboot.kickstart.exceptions.ResourceNotFoundException;
import com.springboot.kickstart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity body) {
        return userRepository.save(body);
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User now found with id: " + id));
    }

    @PutMapping("/{id}")
    public UserEntity updateUserById(@PathVariable Long id, @RequestBody UserEntity body) {
        UserEntity userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User now found with id: " + id));
        userData.setName(body.getName());
        userData.setEmail(body.getEmail());
        return userRepository.save(userData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        UserEntity userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User now found with id: " + id));
        userRepository.delete(userData);

        return ResponseEntity.ok().build();
    }
}
