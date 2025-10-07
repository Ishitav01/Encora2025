package com.example.testingexercises.PrometheusTiming;

import com.example.testingexercises.entity.User;
import com.example.testingexercises.repository.UserRepository;
import com.example.testingexercises.service.UserService;

import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTimer {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    @Test
    void shouldReturnUserName_whenUserExists() {
        Timer timer = registry.timer("userservice.execution.time");

        timer.record(() -> {
            User user = new User(1L, "Alice");
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));

            String result = userService.getUserNameById(1L);

            assertEquals("Alice", result);
            verify(userRepository).findById(1L);
        });

        System.out.println("UserService metrics:\n" + registry.scrape());
    }

    @Test
    void shouldReturnUnknown_whenUserNotFound() {
        Timer timer = registry.timer("userservice.execution.time");

        timer.record(() -> {
            when(userRepository.findById(2L)).thenReturn(Optional.empty());

            String result = userService.getUserNameById(2L);

            assertEquals("Unknown", result);
            verify(userRepository).findById(2L);
        });

        System.out.println("UserService metrics:\n" + registry.scrape());
    }
}
