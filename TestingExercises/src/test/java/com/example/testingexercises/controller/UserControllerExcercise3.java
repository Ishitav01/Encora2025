package com.example.testingexercises.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.testingexercises.entity.User;
import com.example.testingexercises.repository.UserRepository;

@WebMvcTest(UserController.class)
public class UserControllerExcercise3 {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void shouldReturnUser_whenExists() throws Exception{
		when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L,"Alice")));
		
		mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())
        
        .andExpect(jsonPath("$.name").value("Alice"));
	}

}
