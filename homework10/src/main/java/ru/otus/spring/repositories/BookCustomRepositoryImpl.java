package ru.otus.spring.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.spring.domain.Book;

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
}