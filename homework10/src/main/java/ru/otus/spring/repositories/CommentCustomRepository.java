package ru.otus.spring.repositories;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentCustomRepository {
    void add(String bookId, Comment comment);
    void save(String bookId, Comment comment);
    void update(String id, Comment comment);
    List<Comment> findCommentsByBookId(String id);
    Comment findByCommentId(String id);
    void deleteByCommentId(String id);
    void deleteAll();
}
