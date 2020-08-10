package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование AuthorDaoJdbcTest")
@JdbcTest
@Import(TestDaoConfiguration.class)
public class AuthorDaoJdbcTest {

    @Autowired
    AuthorDao authorDao;

    @DisplayName("Создание автора и найти автора по идентификатору")
    @Test
    void createAuthor() {

        long id = authorDao.insertByFullName("Клиффорд Саймак");
        assertThat(authorDao.getById(id).get().getName())
                .isEqualTo("Клиффорд Саймак");
    }

    @DisplayName("Обновление автора по имени")
    @Test
    void updateByName() {

        long id = authorDao.insertByFullName("Андрэ Нортон");
        assertThat(authorDao.getById(id).get().getName()).isEqualTo("Андрэ Нортон");
        authorDao.updateAuthor("Андрэ Нортон", "Клиффорд Саймак");
        assertThat(authorDao.getById(id).get().getName()).isEqualTo("Клиффорд Саймак");
    }

    @DisplayName("Удаление автора по имени")
    @Test
    void deleteByName() {

        long id = authorDao.insertByFullName("Андрэ Нортон");
        assertThat(authorDao.getById(id).get().getName()).isEqualTo("Андрэ Нортон");
        authorDao.deleteByFullName("Андрэ Нортон");
        assertThat(authorDao.getById(id)).isEmpty();
    }
}