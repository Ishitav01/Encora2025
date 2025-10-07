package com.example.testingexercises.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    

    @Test
    void shouldFailValidation_whenAgeTooLow() throws Exception {
        // Arrange
        String json = "{\"name\": \"Tom\", \"age\": 12}";
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    
    /**
     * Test case: Should fail validation when name is blank.
     */
    @Test
    void shouldFailValidation_whenNameIsBlank() throws Exception {
        // Arrange
        String json = "{\"name\": \"\", \"age\": 25}";
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    
    /**
     * Test case: Should fail validation when name is missing.
     */
    @Test
    void shouldFailValidation_whenNameIsMissing() throws Exception {
        // Arrange
        String json = "{\"age\": 25}";
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    
    /**
     * Test case: Should fail validation when age is missing.
     */
    @Test
    void shouldFailValidation_whenAgeIsMissing() throws Exception {
        // Arrange
        String json = "{\"name\": \"John\"}";
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    
    /**
     * Test case: Should succeed validation with valid data.
     */
    @Test
    void shouldSucceedValidation_withValidData() throws Exception {
        // Arrange
        String json = "{\"name\": \"John\", \"age\": 25}";
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Registered John"));
    }
    
    /**
     * Test case: Should accept minimum valid age (18).
     */
    @Test
    void shouldAcceptMinimumValidAge() throws Exception {
        // Arrange
        String json = "{\"name\": \"Alice\", \"age\": 18}";
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Registered Alice"));
    }
    
    /**
     * Test case: Should reject age of 17 (just below minimum).
     */
    @Test
    void shouldRejectAgeBelowMinimum() throws Exception {
        // Arrange
        String json = "{\"name\": \"Bob\", \"age\": 17}";
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    
    /**
     * Test case: Should fail validation when JSON is malformed.
     */
    @Test
    void shouldFailValidation_whenJsonIsMalformed() throws Exception {
        // Arrange
        String json = "{\"name\": \"Test\", \"age\": }";  // Malformed JSON
        
        // Act & Assert
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    
    /**
     * Test case: Should handle bulk registration with valid data.
     */
    @Test
    void shouldHandleBulkRegistration_withValidData() throws Exception {
        // Arrange
        String json = "{\"name\": \"Charlie\", \"age\": 30}";
        
        // Act & Assert
        mockMvc.perform(post("/register/bulk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Bulk registration for Charlie (age: 30)"));
    }
}