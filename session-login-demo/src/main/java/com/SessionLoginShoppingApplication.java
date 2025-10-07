package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan // needed for @WebFilter and @WebListener classes
public class SessionLoginShoppingApplication {
public static void main(String[] args) {
SpringApplication.run(SessionLoginShoppingApplication.class, args);
}
}