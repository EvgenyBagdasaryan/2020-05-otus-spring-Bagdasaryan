package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;

public interface AuthorService {

    void saveAuthor(String bookId, Author author);
    void updateAuthor(String id, Author author);

    Author findByAuthorId(String authorId) throws DocumentNotFoundException;

    List<Author> getAuthorsByBookId(String bookId);
    void deleteById(String id) throws DocumentNotFoundException;
    void deleteAll();
}
