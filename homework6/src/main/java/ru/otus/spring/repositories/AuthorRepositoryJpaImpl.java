package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa  {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select at from Author at", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long id) {

        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {

        Author author = em.find(Author.class, id);
        em.remove(author);
    }
}