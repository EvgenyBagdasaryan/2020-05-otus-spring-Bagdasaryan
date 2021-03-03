package ru.otus.spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

@DataMongoTest
public abstract class TestInitDb {

    @Autowired
    MongoTemplate mongoTemplate;

    @BeforeEach
    void initDb() {
        for (Author author : TestData.getAuthors()) {
            mongoTemplate.save(author);
        }
        for (Genre genre : TestData.getGenres()) {
            mongoTemplate.save(genre);
        }
        for (Book book : TestData.getBooks()) {
            mongoTemplate.save(book);
        }
        for (Comment comment : TestData.getComments()) {
            mongoTemplate.save(comment);
        }
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.dropCollection(Author.class);
        mongoTemplate.dropCollection(Genre.class);
        mongoTemplate.dropCollection(Book.class);
        mongoTemplate.dropCollection(Comment.class);
    }

}
