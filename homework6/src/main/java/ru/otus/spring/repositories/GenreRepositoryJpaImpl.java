package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa  {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre insertByGenre(Genre genre) {
        if (genre.getId() == null) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select at from Genre at", Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findById(long id) {

        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Genre a where a.id = : id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}