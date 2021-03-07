package ru.otus.spring.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@WebFluxTest(BookController.class)
@DisplayName("Контроллер книг")
class BookControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    CommentRepository commentRepository;

    Book bookDikkens = new Book("1", "Оливер Твист",
            List.of(new Genre("1", "юмор") , new Genre("2", "классика")),
            List.of(new Author("1", "Чарлз Диккенс")),
            List.of(new Comment("1", "Текст комментария")));
    Book bookLondon = new Book("2", "Рассказы Севера",
            List.of(new Genre("1", "повесть") , new Genre("2", "классика")),
            List.of(new Author("1", "Джек Лондон")),
            List.of(new Comment("1", "Текст комментария")));

    @Test
    @DisplayName("возвращает все книги")
    void getAll() {
        Flux<Book> books = Flux.fromIterable(List.of(bookDikkens, bookLondon));
        when(bookRepository.findAll()).thenReturn(books);

        webTestClient.get()
                .uri("/books")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(List::size, equalTo(2));
    }

    @Test
    @DisplayName("возвращает книгу по ее ID")
    void getById() {
        Mono<Book> book = Mono.just(bookDikkens);
        when(bookRepository.findById(anyString())).thenReturn(book);

        webTestClient.get()
                .uri("/books/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(book1 -> book1, equalTo(bookDikkens));
    }

    @Test
    @DisplayName("возвращает книгу по ее названию без учета регистра")
    void getByName() {
        Mono<Book> book = Mono.just(bookDikkens);
        when(bookRepository.getByName(anyString())).thenReturn(book);

        webTestClient.get()
                .uri("/books/name/" + bookDikkens.getName())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(book1 -> book1, equalTo(bookDikkens));
    }

    @Test
    @DisplayName("возвращает созданную книгу")
    void create() {
        Mono<Book> dikkMono = Mono.just(bookDikkens);
        when(bookRepository.insert(bookDikkens)).thenReturn(dikkMono);

        webTestClient.post()
                .uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(dikkMono), Book.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DisplayName("обновляет книгу")
    void update() {
        Mono<Book> dikkMono = Mono.just(bookDikkens);
        when(bookRepository.insert(bookDikkens)).thenReturn(dikkMono);

        webTestClient.put()
                .uri("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(dikkMono), Book.class)
                .exchange()
                .expectStatus().isOk();
    }
}