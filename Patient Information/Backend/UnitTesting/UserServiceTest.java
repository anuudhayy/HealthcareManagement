package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserService userService = new UserService(userRepository, passwordEncoder);

    @Test
    void registerUser_ShouldSaveUser_WhenValidDataIsProvided() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole(User.Role.PATIENT);

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        assertTrue(passwordEncoder.matches("password123", savedUser.getPassword()));
        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void authenticateUser_ShouldReturnUser_WhenValidCredentialsAreProvided() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("password123"));
        user.setRole(User.Role.PATIENT);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        boolean isAuthenticated = userService.authenticateUser("testuser", "password123");

        assertTrue(isAuthenticated);
    }
}
