package ru.otus.spring.repositories;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class AuthorCustomRepositoryImpl implements AuthorCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(String bookId, Author author) {
        author.setId(UUID.randomUUID().toString());
        Update update = new Update().push("authors").value(author);
        this.mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(bookId)), update, Book.class);
    }

    @Override
    public void save(String bookId, Author author) {
        if (StringUtils.isEmpty(author.getId())) {
            add(bookId, author);
        } else {
            update(author.getId(), author);
        }
    }

    @Override
    public void update(String id, Author author) {
        author.setId(id);
        Update update = new Update()
            .filterArray("id._id", author.getId())
            .set("authors" + ".$[id]", author);;
        this.mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
    @Override
    public List<Author> findAuthorsByBookId(String id) {
        val aggregation = newAggregation(
            match(Criteria.where("id").is(id))
            , unwind("authors")
            , project().andExclude("_id").and("authors.id").as("_id").and("authors.name").as("name")
        );

        List<Author> allAuthorInBook = mongoTemplate.aggregate(aggregation, Book.class, Author.class).getMappedResults();

        return allAuthorInBook;
    }

    @Override
    public Author findByAuthorId(String id) {

        val aggregation = newAggregation(
                unwind("authors"),
                replaceRoot("authors"),
                match(Criteria.where("_id").is(id)),
                limit(1)
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Author.class).getUniqueMappedResult();
    }

    @Override
    public void deleteByAuthorId(String id) {
        final Update update = new Update().pull("authors", Query.query(Criteria.where("_id").is(id)));
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public void deleteAll() {
        final Update update = new Update().set("authors", new ArrayList<>());
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}