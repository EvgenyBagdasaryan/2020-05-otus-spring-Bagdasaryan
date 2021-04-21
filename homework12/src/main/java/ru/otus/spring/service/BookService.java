package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    void saveBook(Book book);
    String readTable();
    List<Book> getBooks();
    Book getBookById(long id);
    void updateNameById(long id, String name);
    void deleteById(long id);
}
