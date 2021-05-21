package ru.otus.spring.service;

public interface CommentService {
    void createComment(long bookId, String comment);
    void updateComment(long commentId, String comment);
    String readTable();
    void deleteById(long id);
}
