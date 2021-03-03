package ru.otus.spring.repositories;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(String bookId, Comment comment) {
        comment.setId(UUID.randomUUID().toString());
        Update update = new Update().push("comments").value(comment);
        this.mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(bookId)), update, Book.class);
    }

    @Override
    public void save(String bookId, Comment comment) {
        if (StringUtils.isEmpty(comment.getId())) {
            add(bookId, comment);
        } else {
            update(comment.getId(), comment);
        }
    }

    @Override
    public void update(String id, Comment comment) {
        comment.setId(id);
        Update update = new Update()
            .filterArray("id._id", comment.getId())
            .set("comments" + ".$[id]", comment);;
        this.mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
    @Override
    public List<Comment> findCommentsByBookId(String id) {
        val aggregation = newAggregation(
            match(Criteria.where("id").is(id))
            , unwind("comments")
            , project().andExclude("_id").and("comments.id").as("_id").and("comments.name").as("name")
        );

        List<Comment> allCommentInBook = mongoTemplate.aggregate(aggregation, Book.class, Comment.class).getMappedResults();

        return allCommentInBook;
    }

    @Override
    public Comment findByCommentId(String id) {

        val aggregation = newAggregation(
                unwind("comments"),
                replaceRoot("comments"),
                match(Criteria.where("_id").is(id)),
                limit(1)
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Comment.class).getUniqueMappedResult();
    }

    @Override
    public void deleteByCommentId(String id) {
        final Update update = new Update().pull("comments", Query.query(Criteria.where("_id").is(id)));
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public void deleteAll() {
        final Update update = new Update().set("comments", new ArrayList<>());
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}