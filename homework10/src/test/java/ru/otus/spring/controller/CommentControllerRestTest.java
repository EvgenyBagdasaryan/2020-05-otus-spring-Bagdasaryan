package ru.otus.spring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование CommentControllerRest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerRestTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void contextLoads() {

        ResponseEntity responce = rest.getForEntity("/api/comments/" + UUID.randomUUID().toString(), Object.class);

        assertEquals(responce.getStatusCodeValue(), 200);
    }
}