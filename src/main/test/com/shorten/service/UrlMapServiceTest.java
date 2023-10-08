package com.shorten.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlMapServiceTest {
    @Autowired
    private UrlMapService urlMapService;

    @Test
    void encode() {
        //Arrange

        //Act
        String shortKey = urlMapService.encode("https://wwww.baidu.com");

        //Assert
        Assertions.assertNotNull(shortKey);
    }

    @Test
    void decode() {
        //Arrange
        String shortKey = urlMapService.encode("https://wwww.baidu.com");

        //Act
        Optional<String> longUrl = urlMapService.decode(shortKey);

        //Assert
        Assertions.assertEquals("https://wwww.baidu.com", longUrl.get());
    }
}