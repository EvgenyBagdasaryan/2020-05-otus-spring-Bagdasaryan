package ru.otus.spring.repositories;

import ru.otus.spring.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {

    Author save(Author author);
    List<Author> findAll();
    Optional<Author> findById(long id);
    void deleteById(long id);
}
