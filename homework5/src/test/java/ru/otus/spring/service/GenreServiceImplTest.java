package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;

import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса GenreServiceImplTest")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreDao authorDao;

    @MockBean
    private BookDao bookDao;

    @DisplayName("Сохранение жанра")
    @Test
    void createGenre() {
        genreService.saveGenreByName("some author");
        verify(authorDao).insertByName("some author");
    }

    @DisplayName("Отобразить все жанры")
    @Test
    void showTable() {

        genreService.readTableGenres();
        verify(authorDao).getAll();
    }

    @DisplayName("Обновить жанра")
    @Test
    void updateGenre() {

        genreService.updateGenreByName("some full name author first","some full name author second");
        verify(authorDao).updateGenre("some full name author first","some full name author second");
    }

    @DisplayName("Удаление жанра")
    @Test
    void deleteByName() {
        genreService.deleteGenreByName("some genre");
        verify(authorDao).getByName("some genre");
    }
}