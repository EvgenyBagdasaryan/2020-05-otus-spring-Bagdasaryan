package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface AuthorService {

    void saveAuthor(Author author);
    String readTable();
    void deleteById(long id);
    //Author getAuthor();
    Author findAuthorsByBookId(Long id);
}
