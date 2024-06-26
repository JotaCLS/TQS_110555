package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample for a typical Spring Boot application.
 * Offers a REST API with basic endpoints to manage a list of Employees
 *
 */
@SpringBootApplication
public class CarMain {

    public static void main(String[] args) {
        SpringApplication.run(CarMain.class, args);
    }

}