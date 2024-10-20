package com.crio.CoderHack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.CoderHack.dto.UserDTO;
import com.crio.CoderHack.model.User;
import com.crio.CoderHack.repository.UserRepository;

@Service
public class UserServiceImpl {
    
    @Autowired
    private UserRepository userRepository;
        
    // Create user method with DTO
    public UserDTO createUser(UserDTO userDTO){
        User user = new User(null, userDTO.getUsername(), 0, new ArrayList<>());
        User savedUser = userRepository.save(user);
        return entitytoDTO(savedUser);
    }

    // Get all users method with DTO
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(this::entitytoDTO)
                    .collect(Collectors.toList());
    }
    
    // Get user by ID
    public Optional<UserDTO> getUserById(String userId){
        return userRepository.findById(userId).map(this::entitytoDTO);
    }
    
    // Delete user
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    // Update score method with badge logic
    public UserDTO updateScore(String userId, int score){
        User user = userRepository.findById(userId)
                                    .orElseThrow(() -> new RuntimeException("User not found"));
        if(score < 0 || score > 100){
            throw new IllegalArgumentException("Invalid score");
        }
        user.setScore(score);
        updateBadges(user);
        User updatedUser = userRepository.save(user);
        return entitytoDTO(updatedUser);
    }

    // Badge update logic
    private void updateBadges(User user) {
        List<String> badges = new ArrayList<>();
        if(user.getScore() >= 60){
            badges.add("Code Master");
        } else if(user.getScore() >= 30){
            badges.add("Code Champ");
        } else if(user.getScore() > 0){
            badges.add("Code Ninja");
        }
        user.setBadges(badges);
    }
    
    // convert User entity to DTO
    private UserDTO entitytoDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(), 
            user.getScore(), 
            user.getBadges()
            );
    }
    }
    

