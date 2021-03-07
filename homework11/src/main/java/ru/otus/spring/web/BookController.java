package ru.otus.spring.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@Slf4j
class BookController {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @GetMapping(value = "/books")
    public Flux<Book> getAll() {
        log.info("GET: /books");
        return bookRepository.findAll();
    }

    @GetMapping(value = "/books/{id}")
    public Mono<Book> getById(@PathVariable("id") String id) {
        log.info("GET: /books/{}", id);
        return bookRepository.findById(id);
    }

    @GetMapping(value = "/books/name/{name}")
    public Mono<Book> getByName(@PathVariable("name") String name) {
        log.info("GET: /books/name/{}", name);
        return bookRepository.getByName(name);
    }

    @PostMapping(value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> create(@RequestBody Book newBook) {
        log.info("POST: /books");
        return bookRepository.save(newBook);
    }

    @PutMapping(value = "/books/{id}")
    public Mono<Book> update(@PathVariable("id") String id, @RequestBody Book forUpdate) {
        log.info("PUT: /books/{}", id);
        return bookRepository.save(forUpdate);
    }

    @DeleteMapping(value = "/books/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        log.info("DELETE: /books/{}", id);
        return bookRepository.deleteById(id);
    }
}
