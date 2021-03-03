package ru.otus.spring.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private List<Comment> comments = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    Comment comment1;
    Comment comment2;
    Comment comment3;

    Author author1;
    Author author2;
    Author author3;
    Author author4;

    Genre genres1;
    Genre genres2;
    Genre genres3;

    @ChangeSet(order = "001", id = "dropDataBase", author = "eugene", runAlways = true)
    public void dropDb(MongoDatabase mongoDatabase) {

        mongoDatabase.drop();
    }

    @ChangeSet(order = "002", id = "initComments", author = "eugene", runAlways = true)
    public void initComments(MongoTemplate template) {

        comment1 = template.save(new Comment(UUID.randomUUID().toString(),"Увлекательно!"));
        comment2 = template.save(new Comment(UUID.randomUUID().toString(),"Захватывающе!"));
        comment3 = template.save(new Comment(UUID.randomUUID().toString(),"Интересно!"));
    }

    @ChangeSet(order = "003", id = "initAuthors", author = "eugene", runAlways = true)
    public void initAuthors(MongoTemplate template){

        author1 = template.save(new Author(UUID.randomUUID().toString(), "Джанни Родари"));
        author2 = template.save(new Author("23","Чарлз Диккенс"));
        author3 = template.save(new Author(UUID.randomUUID().toString(),"Илья Ильф"));
        author4 = template.save(new Author(UUID.randomUUID().toString(),"Евгений Петров"));
    }

    @ChangeSet(order = "004", id = "initGenres", author = "eugene", runAlways = true)
    public void initGenres(MongoTemplate template){
        genres1 = template.save(new Genre(UUID.randomUUID().toString(),"сказки"));
        genres2 = template.save(new Genre(UUID.randomUUID().toString(),"юмор"));
        genres3 = template.save(new Genre(UUID.randomUUID().toString(),"классика"));
    }

    @ChangeSet(order = "005", id = "initBooksCollection", author = "eugene", runAlways = true)
    public void initBooks(MongoTemplate template) {

        Book book1 = template.save(new Book(UUID.randomUUID().toString(), "Приключения Чиполлино", List.of(genres1, genres2), List.of(author1), List.of(comment1, comment2)));
        Book book2 = template.save(new Book("123", "Оливер Твист", List.of(genres2, genres3), List.of(author2), List.of(comment2, comment3)));
        Book book3 = template.save(new Book(UUID.randomUUID().toString(), "Двенадцать стульев", List.of(genres2, genres3), List.of(author3, author4), List.of(comment2, comment3)));
        Book book4 = template.save(new Book(UUID.randomUUID().toString(), "Одноэтажная Америка", List.of(genres2, genres3), List.of(author3, author4), List.of(comment2, comment3)));
        Book book5 = template.save(new Book(UUID.randomUUID().toString(), "Любовь к жизни", List.of(new Genre("55","повесть")), List.of(new Author("66","Джек Лондон")), List.of(new Comment("77","Увлекательно!")))
        );
    }
}
