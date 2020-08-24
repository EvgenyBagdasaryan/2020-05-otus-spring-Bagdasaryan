package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование GenreDaoJdbcTest")
@JdbcTest
@Import(TestDaoConfiguration.class)
public class GenreDaoJdbcTest {

    @Autowired
    GenreDao genreDao;

    @DisplayName("Создание жанра и найти жанр по идентификатору")
    @Test
    void createGenre() {

        long id = genreDao.insertByName("фантастика");
        assertThat(genreDao.getById(id).get().getName())
                .isEqualTo("фантастика");
    }

    @DisplayName("Обновление жанра по имени")
    @Test
    void updateByName() {

        long id = genreDao.insertByName("фантастика");
        assertThat(genreDao.getById(id).get().getName()).isEqualTo("фантастика");
        genreDao.updateGenre("фантастика", "приключения");
        assertThat(genreDao.getById(id).get().getName()).isEqualTo("приключения");
    }

    @DisplayName("Удаление жанра по имени")
    @Test
    void deleteByName() {

        long id = genreDao.insertByName("фантастика");
        assertThat(genreDao.getById(id).get().getName()).isEqualTo("фантастика");
        genreDao.deleteByName("фантастика");
        assertThat(genreDao.getById(id)).isEmpty();
    }
}