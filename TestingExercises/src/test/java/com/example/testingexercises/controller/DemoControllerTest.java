package com.example.testingexercises.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for DemoController and GlobalExceptionHandler.
 * 
 * Exercise 6: Testing Exception Handling with @ControllerAdvice
 * Goal: Ensure that global exceptions are mapped properly to HTTP responses
 * 
 * Key Concepts:
 * - Exception handling with @ControllerAdvice
 * - Custom error response testing
 * - HTTP status code verification
 * - @WebMvcTest for controller testing
 */
@WebMvcTest(DemoController.class)
class DemoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
   
    @Test
    void shouldReturnNotFoundForMissingUser() throws Exception {
        mockMvc.perform(get("/api/find/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
    

    @Test
    void shouldReturnBadRequestForInvalidArgument() throws Exception {
        mockMvc.perform(get("/api/validate/-5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid request: Value cannot be negative"));
    }
    
  
    @Test
    void shouldReturnOkForValidValue() throws Exception {
        mockMvc.perform(get("/api/validate/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("Valid value: 10"));
    }
    

    @Test
    void shouldReturnInternalServerErrorForGenericException() throws Exception {
        mockMvc.perform(get("/api/error"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error: Something went wrong"));
    }
    
 
    @Test
    void shouldReturnInternalServerErrorForNullPointer() throws Exception {
        mockMvc.perform(get("/api/null"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Null pointer error occurred"));
    }
    
 
    @Test
    void shouldReturnSuccessWhenNoException() throws Exception {
        mockMvc.perform(get("/api/success"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success!"));
    }
    

    @Test
    void shouldHandleDifferentUserIdsConsistently() throws Exception {
    
        mockMvc.perform(get("/api/find/1"))
                .andExpect(status().isNotFound());
        
        mockMvc.perform(get("/api/find/999"))
                .andExpect(status().isNotFound());
        
        mockMvc.perform(get("/api/find/12345"))
                .andExpect(status().isNotFound());
    }
}