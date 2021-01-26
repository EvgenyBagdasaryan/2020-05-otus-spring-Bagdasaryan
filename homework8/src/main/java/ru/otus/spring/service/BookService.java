package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;

public interface BookService {

    void saveBook(Book book);
    List<Book> getBooks();
    Book getBookById(String bookId) throws DocumentNotFoundException;
    List<Genre> getGenresBook(String bookId) throws DocumentNotFoundException;
    List<Comment> getCommentsBook(String bookId) throws DocumentNotFoundException;
    void deleteById(String id) throws DocumentNotFoundException;
}
