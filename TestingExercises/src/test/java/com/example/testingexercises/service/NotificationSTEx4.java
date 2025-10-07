package com.example.testingexercises.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotificationSTEx4 {

	private final NotificationService service = new NotificationService();

	@Test
	void shouldSendEmailAsyncSuccessfully() throws Exception {
		CompletableFuture<String> future = service.sendEmailAsync("test@example.com");
		String result = future.get(1, TimeUnit.SECONDS);
		assertEquals("Email sent to test@example.com", result);
	}

}
