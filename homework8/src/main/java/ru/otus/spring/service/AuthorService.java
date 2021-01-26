package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;

public interface AuthorService {

    void saveAuthor(Author author);
    List<Author> getAuthors();
    void deleteById(String id) throws DocumentNotFoundException;
}
