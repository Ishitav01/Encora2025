package com.example.testingexercises.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UserRequest {

	@NotBlank(message = "Name is required")
	private String name;

	@NotNull(message = "Age is required")
	@Min(value = 18, message = "Age must be at least 18")
	private Integer age;

	// Constructors
	public UserRequest() {
	}

	public UserRequest(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserRequest{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}