package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    int count();
    long insertByFullName(String fullName);
    void insertByAuthor(Author author);
    Optional <Author> getById(long id);
    Optional <Author> getByFullName(String fullName);
    List<Author> getAll();
    void deleteById(long id);
    void updateAuthor(String fullNameOld, String fullNameNew);
    void deleteByFullName(String fullName);
}
