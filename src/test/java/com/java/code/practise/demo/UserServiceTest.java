package com.java.code.practise.demo;

import com.java.code.practise.demo.entity.User;
import com.java.code.practise.demo.repository.UserRepository;
import com.java.code.practise.demo.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldReturnSavedUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Tom");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(1L, savedUser.getId());
        assertEquals("Tom", savedUser.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void findById_existingId_shouldReturnUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Tom");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        assertEquals("Tom", foundUser.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_shouldThrowException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(99L);
        });

        assertEquals("User not found with id 99", exception.getMessage());
        verify(userRepository, times(1)).findById(99L);
    }
}