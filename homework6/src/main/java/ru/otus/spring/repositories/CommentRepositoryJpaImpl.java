package ru.otus.spring.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select cm from Comment cm", Comment.class);
        return query.getResultList();
    }

    /*@Override
    public List<Comment> findAll() {
        return em.createQuery("select cm from Comment cm", Comment.class).getResultList();
    }*/

    /*@Override
    public List<Comment> findAll() {
        return em.createQuery("select cm from Comment cm", Comment.class).getResultList();
    }    */

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment cm where cm.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
