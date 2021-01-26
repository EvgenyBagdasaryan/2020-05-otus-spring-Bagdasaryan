package ru.otus.spring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование BookRepository")
@DataMongoTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Сохранить книгу")
    @Test
    void saveBook() {

        Book book = new Book(
                UUID.randomUUID().toString(),
                "testBook" ,
                List.of(new Genre(UUID.randomUUID().toString(), "testGenre")),
                List.of(new Author(UUID.randomUUID().toString(), "testAuthor")),
                List.of(new Comment(UUID.randomUUID().toString(), "testComment")));
        bookRepository.save(book);
        Assertions.assertThat(bookRepository.findById(book.getId()))
                .get()
                .isEqualToComparingFieldByField(book);
    }
    @DisplayName("Обновить название книги")
    @Test
    void updateBookName() {

        Book book = new Book(
                UUID.randomUUID().toString(),
                "testBook" ,
                List.of(new Genre(UUID.randomUUID().toString(), "testGenre")),
                List.of(new Author(UUID.randomUUID().toString(), "testAuthor")),
                List.of(new Comment(UUID.randomUUID().toString(), "testComment")));
        bookRepository.save(book);
        bookRepository.updateBookNameById(book.getId(), "newTestBook");

        assertThat(bookRepository.findById(book.getId()).get())
                .extracting(Book::getName)
                .isEqualTo("newTestBook");
    }
}