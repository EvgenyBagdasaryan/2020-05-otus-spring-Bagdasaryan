package ru.otus.spring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование BookControllerRest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerRestTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void contextLoads() {

        ResponseEntity responce = rest.getForEntity("/api/books", Object.class);

        assertEquals(responce.getStatusCodeValue(), 200);
    }
}