package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

public interface BookService {

    void saveBook(Book book);
    String readTable();
    void deleteById(long id);
}
