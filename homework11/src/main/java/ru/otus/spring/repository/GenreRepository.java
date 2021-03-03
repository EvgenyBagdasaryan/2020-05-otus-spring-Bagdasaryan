package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

    Mono<Genre> getByName(String name);
}
