package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.TestInitDb;

import ru.otus.spring.TestData;
import ru.otus.spring.domain.Book;

import static ru.otus.spring.TestData.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы с книгами")
class BookRepositoryTest extends TestInitDb {

    @Autowired
    BookRepository bookRepository;

    String update_name = "New name";

    @Test
    @DisplayName("поиск книги по названию")
    void getByName() {
        StepVerifier.create(
                bookRepository.getByName(bookLondon2.getName()))
                .expectNextMatches(book -> book.equals(bookLondon2))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("получение всех книг")
    void getAll() {
        StepVerifier.create(
                bookRepository.findAll())
                .expectNextCount(TestData.getBooks().size())
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("книга по ее ID")
    void getById() {
        StepVerifier.create(
                bookRepository.findById(bookLondon.getId()))
                .expectNextMatches(book -> book.equals(bookLondon))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("создание книги")
    void create() {
        Mono<Book> newBook = bookRepository.save(bookLondon);

        StepVerifier.create(newBook)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();

        StepVerifier.create(
                bookRepository.findAll())
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("обновление книги")
    void update() {
        Book forUpdate = bookDikkens;
        forUpdate.setName(update_name);
        Mono<Book> newBook = bookRepository.save(forUpdate);

        StepVerifier.create(newBook)
                .assertNext(book -> assertEquals(book.getName(), update_name))
                .expectComplete()
                .verify();

        StepVerifier.create(
                bookRepository.getByName(update_name))
                .expectNextCount(1)
                .expectComplete()
                .verify();

        StepVerifier.create(
                bookRepository.getByName(bookDikkens.getName()))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }
}