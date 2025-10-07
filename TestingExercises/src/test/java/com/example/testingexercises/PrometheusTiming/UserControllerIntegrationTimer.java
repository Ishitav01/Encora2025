package com.example.testingexercises.PrometheusTiming;

import com.example.testingexercises.entity.User;
import com.example.testingexercises.repository.UserRepository;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerIntegrationTimer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private Long savedId;

    private final PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        User alice = new User(null, "Alice"); // Let JPA generate ID
        alice = userRepository.save(alice);
        savedId = alice.getId();
    }

    @Test
    void shouldReturnUserFromDatabase() {
        Timer timer = registry.timer("usercontroller.execution.time");

        timer.record(() -> {
            try {
                mockMvc.perform(get("/users/" + savedId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(savedId))
                        .andExpect(jsonPath("$.name").value("Alice"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("UserController metrics:\n" + registry.scrape());
    }

    @Test()
    void shouldReturnNotFoundForMissingUser() {
        Timer timer = registry.timer("usercontroller.execution.time");

        timer.record(() -> {
            try {
                mockMvc.perform(get("/users/99999"))
                        .andExpect(status().isNotFound());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("UserController metrics:\n" + registry.scrape());
    }

    @Test
    void shouldCreateNewUser() {
        Timer timer = registry.timer("usercontroller.execution.time");

        timer.record(() -> {
            try {
                String userJson = "{\"name\":\"Bob\"}";

                mockMvc.perform(post("/users")
                                .contentType("application/json")
                                .content(userJson))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").value("Bob"));

                long count = userRepository.count();
                assert count == 2; // Alice + Bob
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("UserController metrics:\n" + registry.scrape());
    }
}
