package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

public interface AuthorService {

    void saveAuthor(Author author);
    String readTable();
    void deleteById(long id);
}
