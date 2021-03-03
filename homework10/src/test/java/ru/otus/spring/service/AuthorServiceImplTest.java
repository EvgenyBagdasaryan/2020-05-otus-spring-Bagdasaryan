package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@DisplayName("Тестирование AuthorServiceImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @DisplayName("Добавить автора")
    @Test
    void createTest() {
        Author author = new Author(UUID.randomUUID().toString(), "testAuthor");
        authorService.saveAuthor("1", author);
        verify(authorRepository).add("1", author);
    }

    @DisplayName("Изменить автора")
    @Test
    void updateTest() {
        Author author = new Author(UUID.randomUUID().toString(), "testAuthor");
        Author author2 = new Author(UUID.randomUUID().toString(), "testAuthor2");
        authorService.updateAuthor(author.getId(), author2);
        verify(authorRepository).update(author.getId(), author2);
    }

    @DisplayName("Удалить всех авторов")
    @Test
    void deleteAll() {
        authorService.deleteAll();
        verify(authorRepository).deleteAll();
    }
}