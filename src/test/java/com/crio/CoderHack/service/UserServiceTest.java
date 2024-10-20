package com.crio.CoderHack.service;

import com.crio.CoderHack.dto.UserDTO;
import com.crio.CoderHack.model.User;
import com.crio.CoderHack.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("6714c5fbf7e81963c888cb7c", "bububub", 0, new ArrayList<>());
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO(null, "bububub", 0, new ArrayList<>());
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO createdUser = userService.createUser(userDTO);

        assertNotNull(createdUser);
        assertEquals("6714c5fbf7e81963c888cb7c", createdUser.getId());
        assertEquals("bububub", createdUser.getUsername());
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<UserDTO> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("bububub", users.get(0).getUsername());
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<UserDTO> retrievedUser = userService.getUserById(user.getId());

        assertTrue(retrievedUser.isPresent());
        assertEquals("bububub", retrievedUser.get().getUsername());
    }

    @Test
    public void testUpdateScore() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = userService.updateScore(user.getId(), 85);

        assertEquals(85, updatedUser.getScore());
        assertEquals("Code Master", updatedUser.getBadges().get(0)); // Check if badge is updated correctly
    }

    @Test
    public void testUpdateScoreInvalid() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.updateScore(user.getId(), 150); // Invalid score
        });

        assertEquals("Invalid score", exception.getMessage());
    }

    @Test
    public void testUpdateScoreUserNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateScore(user.getId(), 50); // User not found
        });

        assertEquals("User not found", exception.getMessage());
    }
}
