package ru.otus.spring.repositories;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class BookCustomRepositoryImpl implements BookCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateBookNameById(String id, String newName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("name", newName);
        mongoTemplate.updateFirst(query, update, Book.class);
    }

    @Override
    public List<Comment> findCommentsByBookId(String id) {

        val aggregation = newAggregation(
                match(Criteria.where("id").is(id))
                , unwind("comments")
                , project().andExclude("_id").and("comments.id").as("_id").and("comments.name").as("name")
        );

        return mongoTemplate.aggregate(aggregation, Book.class, Comment.class).getMappedResults();
    }
    @Override
    public void addCommentByBookId(String id, Comment comment) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.push("comments", comment);
        mongoTemplate.updateFirst(query, update, Book.class);
    }
    @Override
    public void deleteCommentsByBookId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset("comments");
        mongoTemplate.updateFirst(query, update, Book.class);
    }

    @Override
    public List<Genre> findGenresByBookId(String id) {

        val aggregation = newAggregation(
                match(Criteria.where("id").is(id))
                , unwind("genres")
                , project().andExclude("_id").and("genres.id").as("_id").and("genres.name").as("name")
        );

        return mongoTemplate.aggregate(aggregation, Book.class, Genre.class).getMappedResults();
    }
    @Override
    public void addGenreByBookId(String id, Genre genre) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.push("genres", genre);
        mongoTemplate.updateFirst(query, update, Book.class);
    }
    @Override
    public void deleteGenresByBookId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset("genres");
        mongoTemplate.updateFirst(query, update, Book.class);
    }
}