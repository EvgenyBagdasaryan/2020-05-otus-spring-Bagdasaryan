package ru.otus.spring.repositories;

import lombok.NonNull;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>  {
}
