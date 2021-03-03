package ru.otus.spring.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.CommentRepository;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequiredArgsConstructor
@Slf4j

class CommentController {

    private final CommentRepository commentRepository;

    @GetMapping(value = "/commens")
    public Flux<Comment> getAll() {
        log.info("getAll");
        return commentRepository.findAll();
    }

    @GetMapping(value = "/commens/{id}")
    public Mono<Comment> getById(@PathVariable("id") String id) {
        log.info("getById");
        return commentRepository.findById(id);
    }

    @PostMapping(value = "/commens")
    public Mono<Comment> create(@RequestBody Comment newComment) {
        log.info("create: {}", newComment);
        return commentRepository.save(newComment);
    }

    @PutMapping(value = "/commens/{id}")
    public Mono<Comment> update(@PathVariable("id") String id, @RequestBody Comment forUpdate) {
        log.info("update: {}", forUpdate);
        return commentRepository.save(forUpdate);
    }

    @DeleteMapping(value = "/commens/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        log.info("delete");
        return commentRepository.deleteById(id);
    }
}
