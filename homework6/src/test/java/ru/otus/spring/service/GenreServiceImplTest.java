package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.GenreRepositoryJpa;
import ru.otus.spring.domain.Genre;

import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса GenreServiceImplTest")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepositoryJpa genreRepo;

    @DisplayName("Сохранить жанр")
    @Test
    void createGenre() {
        Genre genreTest = new Genre(null, "фантастика");
        genreService.saveGenre(genreTest);
        verify(genreRepo).insertByGenre(genreTest);
    }

    @DisplayName("Прочитать все жанры")
    @Test
    void readGanres() {

        Genre genreTest1 = new Genre(null, "фантастика");
        Genre genreTest2 = new Genre(null, "детектив");
        genreService.saveGenre(genreTest1);
        genreService.saveGenre(genreTest2);

        String allGanres = genreService.readTable();

        verify(genreRepo).findAll();
    }

    @DisplayName("Удалить жанр по идентификатору")
    @Test
    void deleteById() {
        genreService.deleteById(1L);
        verify(genreRepo).deleteById(1L);
    }
}