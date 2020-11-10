package ru.otus.spring.repositories;

import lombok.NonNull;
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
import java.util.UUID;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class AuthorCustomRepositoryImpl implements AuthorCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(String bookId, Author author) {
        author.setId(UUID.randomUUID().toString());
        Update update = new Update().push("authors").value(author);
        this.mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(bookId)), update, Book.class);
    }
    public void update(String id, Author author) {
        author.setId(id);
        Update update = new Update()
            .filterArray("id._id", author.getId())
            .set("authors" + ".$[id]", author);;
        this.mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
    public List<Author> findAuthorsByBookId(String id) {
        val aggregation = newAggregation(
            match(Criteria.where("id").is(id))
            , unwind("authors")
            , project().andExclude("_id").and("authors.id").as("_id").and("authors.name").as("name")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Author.class).getMappedResults();
    }
}