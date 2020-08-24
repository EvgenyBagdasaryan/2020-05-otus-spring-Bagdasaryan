package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование CommentRepositoryImpl")
@DataJpaTest
@Import(TestJpaConfiguration.class)
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa commentRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Создать комментарий")
    @Test
    void createComment() {
        Comment commentTest = new Comment(null, "Интересно",
                new Book(
                        null,
                        "Заповедник гоблинов",
                        new Genre(null, "фантастика"),
                        new Author(null, "Клиффорд Саймак"))) ;

        commentRepository.save(commentTest);

        assertThat(em.find(Comment.class, commentTest.getId()))
                .isEqualToComparingFieldByField(commentTest);
    }

    @DisplayName("Найти все комментарии")
    @Test
    void findAll() {
        Comment commentTest1 = new Comment(null, "Интересно",
                new Book(
                        null,
                        "Заповедник гоблинов",
                        new Genre(null, "фантастика"),
                        new Author(null, "Клиффорд Саймак"))) ;
        Comment commentTest2 = new Comment(null, "Отличная книга !!",
                new Book(
                        null,
                        "Пикник на обочине",
                        new Genre(null, "фантастика"),
                        new Author(null, "Браться Стругатские"))) ;

        em.persist(commentTest1);
        em.persist(commentTest2);

        assertThat(commentRepository.findAll())
                .containsOnly(commentTest1, commentTest2);
    }

    @DisplayName("Найти комментарий по идентификатору")
    @Test
    void findById() {
        Comment commentTest = new Comment(null, "Интересно",
                new Book(
                        null,
                        "Заповедник гоблинов",
                        new Genre(null, "фантастика"),
                        new Author(null, "Клиффорд Саймак"))) ;
        em.persist(commentTest);

        assertThat(commentRepository.findById(commentTest.getId()))
                .get()
                .isEqualToComparingFieldByField(commentTest);
    }

    @DisplayName("Обновить комментарий")
    @Test
    void updateComment() {
        Comment commentTest = new Comment(null, "Отличная книга !!",
                new Book(
                        null,
                        "Пикник на обочине",
                        new Genre(null, "фантастика"),
                        new Author(null, "Браться Стругатские"))) ;
        em.persist(commentTest);

        commentTest.setComment("CommentNew");
        commentRepository.save(commentTest);

        assertThat(em.find(Comment.class, commentTest.getId()))
                .isEqualToComparingFieldByField(commentTest);
    }

    @DisplayName("Удалить комментарий по идентификатору")
    @Test
    void deleteById() {
        Comment commentTest = new Comment(null, "Отличная книга !!",
                new Book(
                        null,
                        "Пикник на обочине",
                        new Genre(null, "фантастика"),
                        new Author(null, "Браться Стругатские"))) ;
        em.persist(commentTest);
        em.detach(commentTest);

        commentRepository.deleteById(commentTest.getId());

        assertThat(em.find(Comment.class ,commentTest.getId()))
                .isNull();
    }
}