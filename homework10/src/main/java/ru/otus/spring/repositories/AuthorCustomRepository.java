package ru.otus.spring.repositories;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorCustomRepository {
    void add(String bookId, Author author);
    void save(String bookId, Author author);
    void update(String id, Author author);
    List<Author> findAuthorsByBookId(String id);
    Author findByAuthorId(String id);
    void deleteByAuthorId(String id);
    void deleteAll();
}
