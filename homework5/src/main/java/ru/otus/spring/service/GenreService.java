package ru.otus.spring.service;

public interface GenreService {
    void saveGenreByName(String name);
    String readTableGenres();
    void updateGenreByName(String nameOld, String nameNew);
    String deleteGenreByName(String fullName);
}
