package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    @Override
    Iterable<Book> findAll();
}

