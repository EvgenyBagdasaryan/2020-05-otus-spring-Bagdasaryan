package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Comment;

public interface CommentRepository extends CommentCustomRepository, MongoRepository<Comment, String>  {
}