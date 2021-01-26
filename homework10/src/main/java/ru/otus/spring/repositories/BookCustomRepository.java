package ru.otus.spring.repositories;

public interface BookCustomRepository {

    void updateBookNameById(String id, String newName);
}
