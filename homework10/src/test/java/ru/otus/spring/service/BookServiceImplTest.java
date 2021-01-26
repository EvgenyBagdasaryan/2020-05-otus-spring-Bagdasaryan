package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;

import static org.mockito.Mockito.verify;

@DisplayName("Тестирование BookServiceImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @DisplayName("Сохранить книгу")
    @Test
    void save() {

        Book bookTest = new Book(
                        null,
                        "testBook" ,
                        List.of(new Genre(null, "testGenre")),
                        List.of(new Author(null, "testAuthor")),
                        List.of(new Comment(null, "testComment")));

        bookService.saveBook(bookTest);
        verify(bookRepository).save(bookTest);
    }
}