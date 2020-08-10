package ru.otus.spring.service;

public interface AuthorService {
    void saveAuthorByFullName(String fullName);
    String readTable();
    void updateAuthorByFullName(String fullNameOld, String fullNameNew);
    String deleteAuthorByFullName(String fullName);
}
