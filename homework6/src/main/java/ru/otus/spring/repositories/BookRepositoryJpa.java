package ru.otus.spring.repositories;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(long id);
    void deleteById(long id);
}
