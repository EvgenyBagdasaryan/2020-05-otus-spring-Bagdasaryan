package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса BookServiceImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepositoryJpa bookRepo;

    @DisplayName("Сохранение книги")
    @Test
    void createBook() {
        Book bookTest = new Book(
                1L,
                "Заповедник гоблинов",
                new Genre(null, "фантастика"),
                new Author(null, "Клиффорд Саймак"));

        bookService.saveBook(bookTest);
        verify(bookRepo).save(bookTest);
    }

    @DisplayName("Прочитать все книги автора")
    @Test
    void readAllBookByAuthor() {

        Book bookTest = new Book(
                1L,
                "Заповедник гоблинов",
                new Genre(null, "фантастика"),
                new Author(null, "Клиффорд Саймак"));

        Book bookTest2 = new Book(
                2L,
                "Трудно быть богом",
                new Genre(null, "фантастика"),
                new Author(null, "Братья Стругатские"));

        Author testAuthor1 = bookTest.getAuthor();
        bookService.saveBook(bookTest);
        bookService.saveBook(bookTest2);

        bookService.readTableByAuthor(testAuthor1);

        verify(bookRepo).findAll();
    }

    @DisplayName("Удалить книгу по идентификатору")
    @Test
    void deleteById() {
        bookService.deleteById(1L);
        verify(bookRepo).deleteById(1L);
    }
}