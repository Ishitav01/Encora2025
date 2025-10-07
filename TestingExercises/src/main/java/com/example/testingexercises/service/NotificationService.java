package com.example.testingexercises.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class NotificationService {
    

    public CompletableFuture<String> sendEmailAsync(String email) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulate email sending delay
                Thread.sleep(500);
                return "Email sent to " + email;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Error sending email", e);
            }
        });
    }
    

    public CompletableFuture<String> sendBulkEmailAsync(String... emails) {
        CompletableFuture<String>[] futures = new CompletableFuture[emails.length];
        
        for (int i = 0; i < emails.length; i++) {
            final String email = emails[i];
            futures[i] = CompletableFuture.supplyAsync(() -> 
                "Email sent to " + email
            );
        }
        
        return CompletableFuture.allOf(futures)
                .thenApply(v -> "Sent " + emails.length + " emails successfully");
    }

    public CompletableFuture<String> sendEmailWithErrorHandling(String email) {
        return CompletableFuture.supplyAsync(() -> {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Invalid email format");
            }
            return "Email sent to " + email;
        }).exceptionally(ex -> "Failed to send email: " + ex.getMessage());
    }
}