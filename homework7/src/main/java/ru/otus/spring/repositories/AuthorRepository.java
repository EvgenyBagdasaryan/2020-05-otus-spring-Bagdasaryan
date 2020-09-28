package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
