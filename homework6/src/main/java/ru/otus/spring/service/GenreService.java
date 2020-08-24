package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

public interface GenreService {
    void saveGenre(Genre genre);
    String readTable();
    void deleteById(long id);
}
