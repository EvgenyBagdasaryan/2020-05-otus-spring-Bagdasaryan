package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentService {
    void createComment(long bookId, String comment);
    void updateComment(long commentId, String comment);
    List<Comment> readTable();
    void deleteById(long id);
}
