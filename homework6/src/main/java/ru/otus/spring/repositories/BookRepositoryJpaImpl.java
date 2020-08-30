package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa  {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public List<Book> findAll() {

        EntityGraph<?> entityGraph = em.getEntityGraph("books-authors-genres-entity-graph");
        TypedQuery<Book> query = em.createQuery("select s from Book s", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);

        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(long id)
    {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public void deleteById(long id) {

        Book book = em.find(Book.class, id);
        em.remove(book);
    }
}
