package ru.otus.spring.repositories;

import ru.otus.spring.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {

    Genre insertByGenre(Genre genre);
    List<Genre> findAll();
    Optional<Genre> findById(long id);
    void deleteById(long id);
}
