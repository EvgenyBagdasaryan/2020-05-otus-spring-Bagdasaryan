package ru.otus.spring.repositories;

import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    Comment save(Comment comment);
    List<Comment> findAll();
    Optional<Comment> findById(long id);
    void deleteById(long id);
}
