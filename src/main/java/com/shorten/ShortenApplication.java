package com.shorten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShortenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortenApplication.class);
    }
}
