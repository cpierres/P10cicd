package com.openclassrooms.bobapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BobappApplication {
    private static final Logger logger = LoggerFactory.getLogger(BobappApplication.class);

    public static void main(String[] args) {
        logger.info("Starting BobApp Backend... test 2238 feat !");
        logger.info("ceci est la feature de possibilité de tchatter entre blagueurs (branche front-feat-add-tchat");
        SpringApplication.run(BobappApplication.class, args);
    }
}
