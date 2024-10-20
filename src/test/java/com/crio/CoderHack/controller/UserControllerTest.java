package com.crio.CoderHack.controller;

import com.crio.CoderHack.dto.UserDTO;
import com.crio.CoderHack.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDTO = new UserDTO("6714c5fbf7e81963c888cb7c", "bububub", 0, Collections.emptyList());
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userDTO));

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("bububub", response.getBody().get(0).getUsername());
    }

    @Test
    public void testGetUserById() {
        when(userService.getUserById(anyString())).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserDTO> response = userController.getUserById(userDTO.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("bububub", response.getBody().getUsername());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userService.getUserById(anyString())).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userController.getUserById("nonexistent-id");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateUser() {
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("bububub", response.getBody().getUsername());
    }

    @Test
    public void testCreateUserBadRequest() {
        UserDTO invalidUserDTO = new UserDTO(null, null, 0, Collections.emptyList());

        ResponseEntity<UserDTO> response = userController.createUser(invalidUserDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateScore() {
        when(userService.updateScore(anyString(), anyInt())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updatecore(userDTO.getId(), 85);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().getScore());
    }

    @Test
    public void testUpdateScoreBadRequest() {
        when(userService.updateScore(anyString(), anyInt())).thenThrow(new RuntimeException("Invalid score"));

        ResponseEntity<UserDTO> response = userController.updatecore(userDTO.getId(), 150);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(anyString());

        ResponseEntity<Void> response = userController.deleteUser(userDTO.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
