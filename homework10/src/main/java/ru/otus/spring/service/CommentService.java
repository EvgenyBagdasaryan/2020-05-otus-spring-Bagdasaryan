package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;

public interface CommentService {
    void saveComment(String bookId, Comment comment);
    void updateComment(String id, Comment comment);

    Comment findByCommentId(String commentId) throws DocumentNotFoundException;

    List<Comment> getCommentsByBookId(String bookId);
    void deleteById(String id) throws DocumentNotFoundException;
    void deleteAll();
}
