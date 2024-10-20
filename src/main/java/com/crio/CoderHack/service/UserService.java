package com.crio.CoderHack.service;

import java.util.List;
import java.util.Optional;

import com.crio.CoderHack.model.User;

public interface UserService {
    
        // Create a new user with default score and empty badges
    User createUser(User user);

    // Retrieve all users from the leaderboard
    List<User> getAllUsers();

    // Retrieve a specific user by their ID
    Optional<User> getUserById(String userId);

    // Delete a user from the leaderboard by their ID
    void deleteUser(String userId);

    // Update the score of a specific user and update their badges accordingly
    User updateScore(String userId, int score);

    // Update the badges of a user based on their score
    void updateBadges(User user);

}
