package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookOnly;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    int count();
    void insertById(long id, BookOnly book);
    long insertBookByParams(BookOnly book);
    Optional<Book> getById(long id);
    Optional<Book> getByName(String bookName);
    Optional<Book> getByParams(BookOnly book);
    List<Book> getByAuthorName(String AuthorName);
    List<Book> getByGenreName(String genreName);
    List<Book> getAll();
    void deleteBookById(long bookId);
    void deleteAll();
}
