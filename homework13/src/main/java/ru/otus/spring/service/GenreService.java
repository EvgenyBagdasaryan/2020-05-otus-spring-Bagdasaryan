package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

public interface GenreService {
    Genre findByName(String genreName);
}
