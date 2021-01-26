package ru.otus.spring.service;

import lombok.NonNull;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);
    List<Comment> getComments();
    List<Comment> getCommentsByBookID(String bookId);
    void deleteComment(String commentId) throws DocumentNotFoundException;
}
