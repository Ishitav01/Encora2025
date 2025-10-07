package com.example.testingexercises.controller;

import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
public class DemoController {
    

    @GetMapping("/find/{id}")
    public String findUser(@PathVariable Long id) {
        throw new NoSuchElementException("User missing");
    }
    

    @GetMapping("/validate/{value}")
    public String validateValue(@PathVariable int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        return "Valid value: " + value;
    }
    
  
    @GetMapping("/error")
    public String triggerError() {
        throw new RuntimeException("Something went wrong");
    }

    @GetMapping("/null")
    public String triggerNullPointer() {
        String str = null;
        return str.length() + "";  // Intentional NPE
    }
    

    @GetMapping("/success")
    public String success() {
        return "Success!";
    }
}