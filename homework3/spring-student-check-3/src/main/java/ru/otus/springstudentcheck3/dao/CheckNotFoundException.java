package ru.otus.springstudentcheck3.dao;

public class CheckNotFoundException extends RuntimeException {

    public CheckNotFoundException(String message) {
        super(message);
    }
}