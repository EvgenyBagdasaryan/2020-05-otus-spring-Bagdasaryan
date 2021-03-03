package ru.otus.spring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;
import java.util.UUID;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Author author1;
    private Author author2;
    private Author author3;
    private Author author4;

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;

    private Genre genres1;
    private Genre genres2;
    private Genre genres3;

    @ChangeSet(order = "000", id = "dropDB", author = "eugene", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "eugene", runAlways = true)
    public void initAuthors(AuthorRepository repository){
        author1 = repository.save(new Author(UUID.randomUUID().toString(), "Джанни Родари"));
        author2 = repository.save(new Author(UUID.randomUUID().toString(),"Чарлз Диккенс"));
        author3 = repository.save(new Author(UUID.randomUUID().toString(),"Илья Ильф"));
        author4 = repository.save(new Author(UUID.randomUUID().toString(),"Евгений Петров"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "eugene", runAlways = true)
    public void initGenres(GenreRepository repository){
        genres1 = repository.save(new Genre(UUID.randomUUID().toString(),"сказки"));
        genres2 = repository.save(new Genre(UUID.randomUUID().toString(),"юмор"));
        genres3 = repository.save(new Genre(UUID.randomUUID().toString(),"классика"));
    }

    @ChangeSet(order = "003", id = "initComments", author = "eugene", runAlways = true)
    public void initComments(CommentRepository repository){
        comment1 = repository.save(new Comment(UUID.randomUUID().toString(),"Увлекательно!"));
        comment2 = repository.save(new Comment(UUID.randomUUID().toString(),"Захватывающе!"));
        comment3 = repository.save(new Comment(UUID.randomUUID().toString(),"Интересно!"));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "eugene", runAlways = true)
    public void initBooks(BookRepository repository){
        repository.save(new Book(UUID.randomUUID().toString(), "Приключения Чиполлино", List.of(genres1, genres2), List.of(author1), List.of(comment1, comment2)));
        repository.save(new Book(UUID.randomUUID().toString(), "Оливер Твист", List.of(genres2, genres3), List.of(author2), List.of(comment2, comment3)));
        repository.save(new Book(UUID.randomUUID().toString(), "Двенадцать стульев", List.of(genres2, genres3), List.of(author3, author4), List.of(comment2, comment3)));
        repository.save(new Book(UUID.randomUUID().toString(), "Одноэтажная Америка", List.of(genres2, genres3), List.of(author3, author4), List.of(comment2, comment3)));
    }
}