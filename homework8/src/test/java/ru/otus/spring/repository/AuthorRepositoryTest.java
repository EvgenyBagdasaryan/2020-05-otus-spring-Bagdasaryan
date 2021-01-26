package ru.otus.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование AuthorRepository")
@DataMongoTest
@Import(TestRepositoryConfiguration.class)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(Book.class);
    }

    @DisplayName("Добавить автора в книгу")
    @Test
    void addTest() {

        Book book =  mongoTemplate.insert(
                new Book(
                        null,
                        "testBook" ,
                        List.of(new Genre(null, "testGenre")),
                        List.of(new Author(null, "testAuthor")),
                        List.of(new Comment(null, "testComment"))));

        Author author = new Author(null, "testAuthor2");
        authorRepository.add(book.getId(), author);

        Book foundBook = mongoTemplate.findOne(new Query(Criteria
                .where("_id").is(book.getId())
                .and("authors.name").is(author.getName())), Book.class);

        assert foundBook != null;

        assertThat(foundBook.getAuthors())
                .extracting(Author::getName)
                .contains(author.getName());
    }

    @DisplayName("Обновить автора в книге")
    @Test
    void updateTest() {

        Book book =  mongoTemplate.insert(
                new Book(
                        null,
                        "testBook" ,
                        null,
                        List.of(new Author(null, "testAuthor")),
                        null));

        final Author author1 = book.getAuthors().stream().findFirst().orElseThrow(RuntimeException::new);
        final Author author2 = new Author(null, "testAuthor2");

        authorRepository.update(author1.getId(), author2);

        Book foundBook = mongoTemplate.findOne(new Query(Criteria
                .where("_id").is(book.getId())
                .and("authors._id").is(author1.getId())), Book.class);
        assert foundBook != null;

        assertThat(foundBook.getAuthors())
                .extracting(Author::getName)
                .contains(author2.getName());
    }

    @DisplayName("Получить всех авторов")
    @Test
    void findAllTest() {

        Author authors1 = new Author("1", "testAuthor1");
        Author authors2 = new Author("2", "testAuthor2");

        mongoTemplate.insert(authors1);
        mongoTemplate.insert(authors2);

        List<Author> allAuthors = authorRepository.findAll();

        assertThat(allAuthors).contains(authors1);
        assertThat(allAuthors).contains(authors2);
    }

    @DisplayName("Получить всех авторов по книге")
    @Test
    void findByBookIdTest() {
        List<Author> authors = List.of(new Author(UUID.randomUUID().toString(), "testAuthor1"));

        Book book =  mongoTemplate.insert(
                new Book(
                        null,
                        "testBook" ,
                        null,
                        authors,
                        null));

        //mongoTemplate.insert(book);

        //List<Author> foundAuthors = authorRepository.findAuthorsByBookId(book.getId());

        /*assertThat(foundAuthors)
                .extracting(Author::getName)
                .containsExactlyElementsOf(foundAuthors.stream().map(Author::getName).collect(Collectors.toList()));*/
    }
}