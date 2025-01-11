package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ApplicationIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void registerUser_ShouldSaveUserToDatabase_WhenValidDataIsProvided() {
        User user = new User();
        user.setUsername("integrationUser");
        user.setPassword("password");
        user.setRole(User.Role.PATIENT);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals("integrationUser", savedUser.getUsername());
    }
}
