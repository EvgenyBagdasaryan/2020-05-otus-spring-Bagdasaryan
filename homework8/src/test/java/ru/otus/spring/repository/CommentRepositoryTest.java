package ru.otus.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование CommentRepository")
@DataMongoTest
@Import(TestRepositoryConfiguration.class)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoTemplate.dropCollection(Book.class);
    }

    @DisplayName("Добавить комментарий в книгу")
    @Test
    void addTest() {

        Book book =  mongoTemplate.insert(
                new Book(
                        null,
                        "testBook" ,
                        List.of(new Genre(null, "testGenre")),
                        List.of(new Author(null, "testAuthor")),
                        List.of(new Comment(null, "testComment"))));

        Comment сomment = new Comment(book.getId(), "testComment");
        commentRepository.save(сomment);

        Book foundBook = mongoTemplate.findOne(new Query(Criteria
                .where("_id").is(book.getId())
                .and("comments.name").is(сomment.getName())), Book.class);

        assert foundBook != null;

        assertThat(foundBook.getComments())
                .extracting(Comment::getName)
                .contains(сomment.getName());
    }

    @DisplayName("Обновить комментарий в книге")
    @Test
    void updateTest() {

        /*Comment сomment = new Comment(null, "testComment");
        Comment сomment2 = new Comment(null, "newTestComment");

        ////-----

        mongoTemplate.insert(сomment);*/
        //mongoTemplate.insert(сomment2);

        //List<Comment> allComments = commentRepository.findAll();

        //assertThat(allComments).contains(сomment);
        //assertThat(allComments).contains(сomment2);

        ///------

       /* Book book =  mongoTemplate.insert(
                new Book(
                        null,
                        "testBook" ,
                        null,
                        null,
                        List.of(сomment)));

        commentRepository.updateCommentByID(сomment.getId(), сomment2);

        Book foundBook = mongoTemplate.findOne(new Query(Criteria
                .where("_id").is(book.getId())
                .and("comments.name").is(сomment.getName())), Book.class);

        assert foundBook != null;*/

        /*assertThat(foundBook.getComments())
                .containsOnly(сomment2);
*/
        //-------------------------

        /*Comment comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .text("comment 1").build();

        Book book = mongoTemplate.insert(Book.builder()
                .name("Test book 2")
                .comments(List.of(comment))
                .build());

        final Comment newComment = Comment.builder().text("newComment").build();

        commentRepository.update(comment.getId(), newComment);

        Book foundBook = mongoTemplate.findOne(new Query(Criteria
                .where("_id").is(book.getId())
                .and("comments._id").is(comment.getId())), Book.class);
        assert foundBook != null;

        assertThat(foundBook.getComments())
            .containsOnly(newComment);*/
    }

    /*@DisplayName("Получить все комментарии")
    @Test
    void findAllTest() {
        final List<Comment> comments1 = List.of(Comment.builder()
                .id(UUID.randomUUID().toString())
                .text("Comment 1").build());

        final List<Comment> comments2 = List.of(Comment.builder()
                .id(UUID.randomUUID().toString())
                .text("Comment2").build());

        mongoTemplate.insert(Book.builder()
                .name("Test book 1")
                .comments(comments1)
                .build());

        mongoTemplate.insert(Book.builder()
                .name("Test book 2")
                .comments(comments2)
                .build());

        Page<CommentDto> page = commentRepository.findAll(PageRequest.of(0, 10));

        assertThat(page.getContent())
                .extracting(CommentDto::getText)
                .containsAll(comments1.stream().map(Comment::getText).collect(Collectors.toList()))
                .containsAll(comments2.stream().map(Comment::getText).collect(Collectors.toList()));
    }

    @DisplayName("Получить все комментарии по книге")
    @Test
    void findByBookIdTest() {
        final Comment comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .text("Comment1").build();

        final Book book = Book.builder()
                .name("Test book 1")
                .comments(List.of(comment))
                .build();

        mongoTemplate.insert(book);

        Page<CommentDto> page = commentRepository.findByBookId(book.getId(), PageRequest.of(0, 10));

        assertThat(page.getContent().stream().findFirst())
                .get()
                .extracting(CommentDto::getText)
                .isEqualTo(comment.getText());
    }

    @DisplayName("Удалить комментарий")
    @Test
    void deleteByIdTest() {
        final Comment comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .text("Comment1").build();

        Book book = Book.builder()
                .name("Test book 1")
                .comments(List.of(comment))
                .build();

        mongoTemplate.insert(book);
        commentRepository.deleteById(comment.getId());

        assertThat(mongoTemplate.find(new Query(Criteria.where("comments._id").is(comment.getId())), Book.class))
                .isEmpty();
    }

    @DisplayName("Удалить все комментарии")
    @Test
    void deleteAllTest() {
        List<Comment> comments1 = List.of(Comment.builder()
                .id(UUID.randomUUID().toString())
                .text("Comment 1").build());

        List<Comment> comments2 = List.of(Comment.builder()
                .id(UUID.randomUUID().toString())
                .text("Comment2").build());

        mongoTemplate.insert(Book.builder()
                .name("Test book")
                .comments(comments1)
                .build());
        mongoTemplate.insert(Book.builder()
                .name("Test book")
                .comments(comments2)
                .build());

        commentRepository.deleteAll();

        Aggregation aggregation = newAggregation(Aggregation.unwind("comments"), Aggregation.count().as("total"));
        CountDto countDto = mongoTemplate.aggregate(aggregation, Book.class, CountDto.class)
                .getUniqueMappedResult();

        assertThat(countDto) //будет null если нет элементов
            .isNull();
    }*/
}
