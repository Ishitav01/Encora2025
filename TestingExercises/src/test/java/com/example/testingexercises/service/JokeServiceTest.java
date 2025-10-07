package com.example.testingexercises.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

/**
 * Test class for JokeService.
 * 
 * Exercise 7: Testing REST Client (RestTemplate / WebClient)
 * Goal: Mock external HTTP calls (like calling another microservice)
 * 
 * Key Concepts:
 * - MockRestServiceServer for mocking external HTTP calls
 * - Testing RestTemplate without real network calls
 * - Verifying request URLs and response handling
 * - Testing error scenarios in REST clients
 */
@SpringBootTest
class JokeServiceTest {
    
    @Autowired
    private JokeService jokeService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private MockRestServiceServer mockServer;
    
    /**
     * Setup method - runs before each test.
     * Initializes MockRestServiceServer.
     */
    @BeforeEach
    void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }
    
    /**
     * Test case: Should return mocked joke from external API.
     * 
     * This test verifies:
     * 1. RestTemplate makes HTTP request to correct URL
     * 2. Mocked response is returned
     * 3. No actual network call is made
     */
    @Test
    void shouldReturnMockedJoke() {
       
        String mockResponse = "{\"value\":\"Chuck Norris can divide by zero\"}";
        
        mockServer.expect(requestTo("https://api.chucknorris.io/jokes/random"))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
        
       
        String result = jokeService.getJoke();
        
       
        assertNotNull(result);
        assertTrue(result.contains("Chuck Norris can divide by zero"));
        
       
        mockServer.verify();
    }
    
    /**
     * Test case: Should handle different joke responses.
     */
    @Test
    void shouldHandleDifferentJokeResponses() {
        // Arrange
        String mockResponse = "{\"value\":\"Funny joke\"}";
        
        mockServer.expect(requestTo("https://api.chucknorris.io/jokes/random"))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
        
        // Act
        String result = jokeService.getJoke();
        
        // Assert
        assertTrue(result.contains("Funny joke"));
        mockServer.verify();
    }
    
    /**
     * Test case: Should return joke value.
     */
    @Test
    void shouldReturnJokeValue() {
        // Arrange
        String mockResponse = "{\"value\":\"Test joke\"}";
        
        mockServer.expect(requestTo("https://api.chucknorris.io/jokes/random"))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
        
        // Act
        String result = jokeService.getJokeValue();
        
        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Test joke"));
        mockServer.verify();
    }
    
    /**
     * Test case: Should fetch joke from custom URL.
     */
    @Test
    void shouldFetchJokeFromCustomUrl() {
        // Arrange
        String customUrl = "https://custom-api.com/jokes";
        String mockResponse = "{\"value\":\"Custom joke\"}";
        
        mockServer.expect(requestTo(customUrl))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
        
        // Act
        String result = jokeService.getJokeFromUrl(customUrl);
        
        // Assert
        assertTrue(result.contains("Custom joke"));
        mockServer.verify();
    }
    
    /**
     * Test case: Should handle server error responses.
     */
    @Test
    void shouldHandleServerError() {
        // Arrange
        mockServer.expect(requestTo("https://api.chucknorris.io/jokes/random"))
                .andRespond(withServerError());
        
        // Act & Assert
        assertThrows(Exception.class, () -> jokeService.getJoke());
        mockServer.verify();
    }
    
    /**
     * Test case: Should handle bad request responses.
     */
    @Test
    void shouldHandleBadRequest() {
        // Arrange
        mockServer.expect(requestTo("https://api.chucknorris.io/jokes/random"))
                .andRespond(withBadRequest());
        
        // Act & Assert
        assertThrows(Exception.class, () -> jokeService.getJoke());
        mockServer.verify();
    }
    
    /**
     * Test case: Should verify HTTP method is GET.
     */
    @Test
    void shouldUseGetMethod() {
        // Arrange
        String mockResponse = "{\"value\":\"Test\"}";
        
        mockServer.expect(method(org.springframework.http.HttpMethod.GET))
                .andExpect(requestTo("https://api.chucknorris.io/jokes/random"))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
        
        // Act
        jokeService.getJoke();
        
        // Assert
        mockServer.verify();
    }
    
 
}