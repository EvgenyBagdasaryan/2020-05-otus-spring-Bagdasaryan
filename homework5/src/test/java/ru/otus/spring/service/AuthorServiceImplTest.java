package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;

import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса AuthorServiceImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private BookDao bookDao;

    @DisplayName("Сохранение автора")
    @Test
    void createAuthor() {
        authorService.saveAuthorByFullName("some author");
        verify(authorDao).insertByFullName("some author");
    }

    @DisplayName("Отобразить всех авторов")
    @Test
    void showTable() {
        authorService.readTable();
        verify(authorDao).getAll();
    }

    @DisplayName("Обновить автора")
    @Test
    void updateAuthor() {
        authorService.updateAuthorByFullName("some full name author first","some full name author second");
        verify(authorDao).updateAuthor("some full name author first","some full name author second");
    }

    @DisplayName("Удаление автора")
    @Test
    void deleteByName () {
        authorService.saveAuthorByFullName("some author");
        authorService.deleteAuthorByFullName("some author");
        verify(authorDao).getByFullName("some author");
    }
}