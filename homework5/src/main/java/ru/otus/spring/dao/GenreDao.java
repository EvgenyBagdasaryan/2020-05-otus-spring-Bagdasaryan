package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreDao {

    int count();
    void insert(Genre genre);
    long insertByName(String genreName);
    Genre getById2(long id);
    Optional<Genre> getByName(String genreName);
    Optional<Genre> getById(long id);
    List<Genre> getAll();
    void updateGenre(String oldNameGenre, String newNameGenre);
    void deleteByName(String name);
    void deleteById(long id);
}
