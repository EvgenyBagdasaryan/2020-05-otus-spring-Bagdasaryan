package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookOnly;

import java.util.Optional;

public interface BookService {

    void createBook(BookOnly book);
    String readBooksTable();
    String readBook(BookOnly bookName);
    String readBookById(long id);
    String readBookByBookName(String bookName);
    Optional<Book> readBookByParam(BookOnly book);
    void updateBook(BookOnly bookOld, BookOnly bookNew);
    void updateBookById(long id, BookOnly bookNew);
    void deleteBook(BookOnly book);
    void deleteBookById(long Id);
}
