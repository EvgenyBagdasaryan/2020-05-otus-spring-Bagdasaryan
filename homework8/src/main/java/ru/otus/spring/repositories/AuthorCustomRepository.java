package ru.otus.spring.repositories;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorCustomRepository {
    void add(String bookId, Author author);
    void update(String id, Author author);
    List<Author> findAuthorsByBookId(String id);
    //Page<AuthorDto> findAll(Pageable pageable);
    //Page<AuthorDto> findByBookId(@NonNull String bookId, Pageable pageable);
    //void deleteById(String id);
    //void deleteAll();
}
