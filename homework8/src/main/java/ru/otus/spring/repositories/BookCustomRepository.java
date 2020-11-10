package ru.otus.spring.repositories;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface BookCustomRepository {

    void updateBookNameById(String id, String newName);

    List<Comment> findCommentsByBookId(String id);
    void addCommentByBookId(String id, Comment comment);
    void deleteCommentsByBookId(String id);

    List<Genre> findGenresByBookId(String id);
    void addGenreByBookId(String id, Genre genre);
    void deleteGenresByBookId(String id);
}
