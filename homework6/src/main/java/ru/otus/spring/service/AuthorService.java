package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    void saveAuthor(Author author);
    List<Author> readTable();
    void deleteById(long id);
}
