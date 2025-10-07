package com.example.testingexercises.service;
import com.example.testingexercises.entity.User;
import com.example.testingexercises.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
   
    @Test
    void shouldReturnUserName_whenUserExists() {
       
        User user = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
       
        String result = userService.getUserNameById(1L);
        
        assertEquals("Alice", result);
        verify(userRepository).findById(1L);
    }
    
   @Test
    void shouldReturnUnknown_whenUserNotFound() {
    
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        
     
        String result = userService.getUserNameById(2L);
        
    
        assertEquals("Unknown", result);
        verify(userRepository).findById(2L);
    }
}