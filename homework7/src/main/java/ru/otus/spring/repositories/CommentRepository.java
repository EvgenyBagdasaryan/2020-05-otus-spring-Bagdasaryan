package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"book"})
    @Override
    Iterable<Comment> findAll();
}
