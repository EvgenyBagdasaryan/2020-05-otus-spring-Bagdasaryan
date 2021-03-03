package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    Mono<Comment> getByName(String name);
}
