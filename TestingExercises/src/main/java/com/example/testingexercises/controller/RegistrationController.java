package com.example.testingexercises.controller;

import com.example.testingexercises.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for user registration.
 * Demonstrates Bean Validation with @Valid annotation.
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {
    
    /**
     * POST /register
     * Registers a new user with validation.
     * 
     * @param userRequest the user registration request (validated)
     * @return ResponseEntity with success message
     */
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest) {
        // Business logic would go here
        return ResponseEntity.ok("Registered " + userRequest.getName());
    }
    
    /**
     * POST /register/bulk
     * Registers multiple users with validation.
     * 
     * @param userRequest the user registration request
     * @return ResponseEntity with success message
     */
    @PostMapping("/bulk")
    public ResponseEntity<String> registerBulk(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok("Bulk registration for " + userRequest.getName() + 
                " (age: " + userRequest.getAge() + ")");
    }
}