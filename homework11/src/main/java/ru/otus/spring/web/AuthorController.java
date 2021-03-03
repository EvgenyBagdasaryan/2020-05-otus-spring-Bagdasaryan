package ru.otus.spring.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@Slf4j
class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping(value = "/authors")
    public Flux<Author> getAll() {
        log.info("getAll");
        return authorRepository.findAll();
    }

    @GetMapping(value = "/authors/{id}")
    public Mono<Author> getById(@PathVariable("id") String id) {
        log.info("getById");
        return authorRepository.findById(id);
    }

    @PostMapping(value = "/authors")
    public Mono<Author> create(@RequestBody Author newAuthor) {
        log.info("create: {}", newAuthor);
        return authorRepository.insert(newAuthor);
    }

    @PutMapping(value = "/authors/{id}")
    public Mono<Author> update(@PathVariable("id") String id, @RequestBody Author forUpdate) {
        log.info("update: {}", forUpdate);
        return authorRepository.save(forUpdate);
    }
}
