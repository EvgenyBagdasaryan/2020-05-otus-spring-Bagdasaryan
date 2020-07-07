package ru.otus.spring.dao;

public class CheckNotFoundException extends RuntimeException {

    public CheckNotFoundException(String message) {
        super(message);
    }
}