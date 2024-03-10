package com.springBootPractice.database;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class FoodInventedApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodInventedApiApplication.class, args);
    }
}
