package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    void saveGenre(Genre genre);
    List<Genre> readTable();
    void deleteById(long id);
}
