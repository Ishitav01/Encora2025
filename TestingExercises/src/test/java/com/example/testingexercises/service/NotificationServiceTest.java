package com.example.testingexercises.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Exercise 4: Testing Service with Async (CompletableFuture)
 * Goal: Test asynchronous service logic using CompletableFuture
 * 
 * Key Concepts:
 * - CompletableFuture.get() with timeout
 * - Testing async operations
 * - Exception handling in async code
 * - Timeout handling
 */


@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    
    private final NotificationService service = new NotificationService();
    
    /**
     * Test case: Should send email asynchronously and return success message.
     * 
     * This test verifies:
     * 1. Async operation completes successfully
     * 2. Result is returned within timeout
     * 3. Message format is correct
     */
    
    
    @Test
    void shouldSendEmailAsyncSuccessfully() throws Exception {
        // Act: Call async method
        CompletableFuture<String> future = service.sendEmailAsync("test@example.com");
        
        // Assert: Wait for result with timeout
        String result = future.get(1, TimeUnit.SECONDS);
        
        // Verify
        assertEquals("Email sent to test@example.com", result);
        assertTrue(future.isDone());
        assertFalse(future.isCompletedExceptionally());
    }
    

    @Test
    void shouldSendMultipleEmailsAsync() throws Exception {
        // Act
        CompletableFuture<String> future1 = service.sendEmailAsync("user1@example.com");
        CompletableFuture<String> future2 = service.sendEmailAsync("user2@example.com");
        
      
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
        allFutures.get(2, TimeUnit.SECONDS);
        
        // Assert
        assertEquals("Email sent to user1@example.com", future1.get());
        assertEquals("Email sent to user2@example.com", future2.get());
    }
 
    @Test
    void shouldSendBulkEmailsAsync() throws Exception {
        // Arrange
        String[] emails = {"user1@test.com", "user2@test.com", "user3@test.com"};
        
        // Act
        CompletableFuture<String> future = service.sendBulkEmailAsync(emails);
        String result = future.get(2, TimeUnit.SECONDS);
        
        // Assert
        assertEquals("Sent 3 emails successfully", result);
    }
    

    @Test
    void shouldHandleErrorsInAsyncOperation() throws Exception {
        // Act
        CompletableFuture<String> future = service.sendEmailWithErrorHandling("");
        String result = future.get(1, TimeUnit.SECONDS);
        
        // Assert
        assertTrue(result.contains("Failed to send email"));
        assertTrue(result.contains("Email cannot be empty"));
    }

    @Test
    void shouldValidateEmailFormatAsync() throws Exception {
        // Act
        CompletableFuture<String> future = service.sendEmailWithErrorHandling("invalid-email");
        String result = future.get(1, TimeUnit.SECONDS);
        
        // Assert
        assertTrue(result.contains("Invalid email format"));
    }
    

    @Test
    void shouldCompleteSuccessfullyWithValidEmail() throws Exception {
        // Act
        CompletableFuture<String> future = service.sendEmailWithErrorHandling("valid@example.com");
        String result = future.get(1, TimeUnit.SECONDS);
        
        // Assert
        assertEquals("Email sent to valid@example.com", result);
    }
    

    @Test
    void shouldTestTimeoutBehavior() {
        // Act
        CompletableFuture<String> future = service.sendEmailAsync("test@example.com");
        
       
        assertDoesNotThrow(() -> future.get(2, TimeUnit.SECONDS));
    }
    
    @Test
    void shouldChainAsyncOperations() throws Exception {
     
        CompletableFuture<String> chainedFuture = service.sendEmailAsync("test@example.com")
                .thenApply(result -> result.toUpperCase())
                .thenApply(result -> result + " - PROCESSED");
        
        String result = chainedFuture.get(2, TimeUnit.SECONDS);
        
        // Assert
        assertEquals("EMAIL SENT TO TEST@EXAMPLE.COM - PROCESSED", result);
    }
}