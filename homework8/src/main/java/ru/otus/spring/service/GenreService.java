package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;

public interface GenreService {

    void saveGenre(Genre genre);
    List<Genre> getGenres();
    List<Genre> getGenresByBookID(String bookId);
    void deleteById(String id) throws DocumentNotFoundException;
}
