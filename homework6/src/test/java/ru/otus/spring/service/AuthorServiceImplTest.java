package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.AuthorRepositoryJpa;
import ru.otus.spring.domain.Author;


import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Тест сервиса AuthorServiceImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepositoryJpa authorRepo;

    @DisplayName("Сохранить автора")
    @Test
    void createAuthor() {
        Author testAuthor = new Author(null, "Клиффорд Саймак");
        authorService.saveAuthor(testAuthor);
        verify(authorRepo).insertByAuthor(testAuthor);
    }

    @DisplayName("Прочитать всех авторов")
    @Test
    void readAuthors() {

        Author testAuthor1 = new Author(null, "Клиффорд Саймак");
        Author testAuthor2 = new Author(null, "Роджер Желязны");
        authorService.saveAuthor(testAuthor1);
        authorService.saveAuthor(testAuthor2);

        String allAuthors = authorService.readTable();

        verify(authorRepo).findAll();
    }

    @DisplayName("Обновить автора")
    @Test
    void updateAuthor() {

        Author testAuthor1 = new Author(1L, "Клиффорд Саймак");
        Author testAuthor2 = new Author(1L, "Роджер Желязны");
        authorService.saveAuthor(testAuthor1);
        verify(authorRepo).insertByAuthor(testAuthor1);
        authorService.saveAuthor(testAuthor2);
        verify(authorRepo).insertByAuthor(testAuthor2);
    }

    @DisplayName("Удалить автора по идентификатору")
    @Test
    void deleteById() {
        authorService.deleteById(1L);
        verify(authorRepo).deleteById(1L);
    }
}