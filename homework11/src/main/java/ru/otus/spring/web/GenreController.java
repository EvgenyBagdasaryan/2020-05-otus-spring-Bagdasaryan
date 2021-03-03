package ru.otus.spring.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@Slf4j
class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping(value = "/genres")
    public Flux<Genre> getAll() {
        log.info("getAll");
        return genreRepository.findAll();
    }

    @GetMapping(value = "/genres/{id}")
    public Mono<Genre> getById(@PathVariable("id") String id) {
        log.info("getById");
        return genreRepository.findById(id);
    }

    @PostMapping(value = "/genres")
    public Mono<Genre> create(@RequestBody Genre newGenre) {
        log.info("create: {}", newGenre);
        return genreRepository.save(newGenre);
    }

    @PutMapping(value = "/genres/{id}")
    public Mono<Genre> update(@PathVariable("id") String id, @RequestBody Genre forUpdate) {
        log.info("update: {}", forUpdate);
        return genreRepository.save(forUpdate);
    }

    @DeleteMapping(value = "/genres/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        log.info("delete");
        return genreRepository.deleteById(id);
    }
}
