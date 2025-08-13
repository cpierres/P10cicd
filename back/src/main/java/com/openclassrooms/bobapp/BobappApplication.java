package com.openclassrooms.bobapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BobappApplication {
    private static final Logger logger = LoggerFactory.getLogger(BobappApplication.class);

    public static void main(String[] args) {
        logger.info("Starting BobApp Backend...");
        SpringApplication.run(BobappApplication.class, args);
    }
}
