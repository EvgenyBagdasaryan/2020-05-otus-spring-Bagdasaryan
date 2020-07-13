package ru.otus.springstudentcheck4.dao;

public class CheckNotFoundException extends RuntimeException {

    public CheckNotFoundException(String message) {
        super(message);
    }
}