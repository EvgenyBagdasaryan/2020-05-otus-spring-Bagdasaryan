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
        author1 = repository.save(new Author("1", "Джанни Родари"));
        author2 = repository.save(new Author("2","Чарлз Диккенс"));
        author3 = repository.save(new Author("3","Илья Ильф"));
        author4 = repository.save(new Author("4","Евгений Петров"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "eugene", runAlways = true)
    public void initGenres(GenreRepository repository){
        genres1 = repository.save(new Genre("1","сказки"));
        genres2 = repository.save(new Genre("2","юмор"));
        genres3 = repository.save(new Genre("3","классика"));
    }

    @ChangeSet(order = "003", id = "initComments", author = "eugene", runAlways = true)
    public void initComments(CommentRepository repository){
        comment1 = repository.save(new Comment("1","Увлекательно!"));
        comment2 = repository.save(new Comment("2","Захватывающе!"));
        comment3 = repository.save(new Comment("3","Интересно!"));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "eugene", runAlways = true)
    public void initBooks(BookRepository repository){
        repository.save(new Book(null, "Приключения Чиполлино", List.of(genres1, genres2), List.of(author1), List.of(comment1, comment2)));
        repository.save(new Book(null, "Оливер Твист", List.of(genres2, genres3), List.of(author2), List.of(comment2, comment3)));
        repository.save(new Book(null, "Двенадцать стульев", List.of(genres2, genres3), List.of(author3, author4), List.of(comment2, comment3)));
        repository.save(new Book(null, "Одноэтажная Америка", List.of(genres2, genres3), List.of(author3, author4), List.of(comment2, comment3)));

        /*repository.save(new Book(null, "Приключения Чиполлино", List.of(genres1, genres2), author1, List.of(comment1, comment2)));
        repository.save(new Book(null, "Оливер Твист", List.of(genres2, genres3), author2, List.of(comment2, comment3)));
        repository.save(new Book(null, "Двенадцать стульев", List.of(genres2, genres3), author3, List.of(comment2, comment3)));
        repository.save(new Book(null, "Одноэтажная Америка", List.of(genres2, genres3), author4, List.of(comment2, comment3)));*/
    }
}