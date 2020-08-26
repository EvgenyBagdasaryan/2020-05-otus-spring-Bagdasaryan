package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    void saveBook(Book book);
    List<Book> readTable();
    List<Book> readTableByAuthor(Author author);
    void deleteById(long id);
}
