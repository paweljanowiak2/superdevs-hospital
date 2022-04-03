package com.superdevs.hospitalmigration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@Slf4j
@SpringBootApplication
public class App {
    public static final String INITIAL_SECURITY_KEY = UUID.randomUUID().toString();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        log.info("INITIAL SECURITY KEY IS: {}", INITIAL_SECURITY_KEY);
    }

}
