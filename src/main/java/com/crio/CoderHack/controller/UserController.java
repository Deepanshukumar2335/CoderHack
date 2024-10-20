package com.crio.CoderHack.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.CoderHack.dto.UserDTO;
import com.crio.CoderHack.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController{
    
    @Autowired
    private UserServiceImpl userService;
    
    // Get all users, sorted by score in descending order
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();

        // Sort users in descending order based on their score
        users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));

        // Return the sorted list of users with an HTTP 200 OK status
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
        Optional<UserDTO> user = userService.getUserById(userId);

        // If the user is found, return it with an HTTP 200 OK status
        // Otherwise, return an HTTP 404 Not Found status
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Create a new user
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        // Validate the incoming request
        if (userDTO.getUsername() == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDTO createdUser = userService.createUser(userDTO);

        // Return the created user with an HTTP 201 Created status
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
        
    // Update user score
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updatecore(@PathVariable String userId, @RequestParam int score) {
        try {
            UserDTO updatedUser = userService.updateScore(userId, score);
            
            // Return the updated user with an HTTP 200 OK status
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            // If an error occurs, return an HTTP 400 Bad Request status
            return ResponseEntity.badRequest().build();
        }
    }
        
    // Delete a user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);

        // Return an HTTP 204 No Content status
        return ResponseEntity.noContent().build();
    }
    

}
