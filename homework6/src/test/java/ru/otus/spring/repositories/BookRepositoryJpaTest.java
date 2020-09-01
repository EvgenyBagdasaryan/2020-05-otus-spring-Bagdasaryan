package ru.otus.spring.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование BookRepositoryJpaTest")
@DataJpaTest
@Import(TestJpaConfiguration.class)
public class BookRepositoryJpaTest {

    @Autowired
    BookRepositoryJpa bookRepo;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Создание книги")
    @Test
    void createBook() {
        Book bookTest = new Book(
                null,
                "Заповедник гоблинов",
                new Genre(null, "фантастика"),
                new Author(null, "Клиффорд Саймак", null));

        bookRepo.save(bookTest);

        assertThat(em.find(Book.class, bookTest.getId()))
                .isEqualToComparingFieldByField(bookTest);
    }

    @DisplayName("Найти все книги")
    @Test
    void findAll() {
        Book bookTest1 = new Book(
                null,
                "Заповедник гоблинов",
                new Genre(null, "фантастика"),
                new Author(null, "Клиффорд Саймак", null));

        Book bookTest2 = new Book(
                null,
                "Пикник на обочине",
                new Genre(null, "фантастика"),
                new Author(null, "Браться Стругатские", null));

        em.persist(bookTest1);

        em.persist(bookTest2);

        Assertions.assertThat(bookRepo.findAll())
                .contains(bookTest1, bookTest2);
    }

    @DisplayName("Найти книгу по идентификатору")
    @Test
    void findById() {
        Book bookTest1 = new Book(
                null,
                "Заповедник гоблинов",
                new Genre(null, "фантастика"),
                new Author(null, "Клиффорд Саймак", null));
        em.persist(bookTest1);
        Optional<Book> optionalBook = bookRepo.findById(bookTest1.getId());

        Assertions.assertThat(optionalBook).get()
                .isEqualTo(bookTest1);
    }

    @DisplayName("Изменение книги")
    @Test
    void update() {
        Book bookTest = new Book(
                null,
                "Пикник на обочине",
                new Genre(null, "фантастика"),
                new Author(null, "Браться Стругатские", null));

        em.persist(bookTest);

        bookTest.setName("451 градус по Фаренгейту");
        bookRepo.save(bookTest);

        assertThat(em.find(Book.class, bookTest.getId())).isEqualToComparingFieldByField(bookTest);
    }

    @DisplayName("Удаление книги по идентификатору")
    @Test
    void deleteById() {
        Book bookTest = new Book(
                null,
                "Пикник на обочине",
                new Genre(null, "фантастика"),
                new Author(null, "Браться Стругатские", null));

        em.persist(bookTest);

        em.detach(bookTest);

        bookRepo.deleteById(bookTest.getId());

        assertThat(em.find(Book.class, bookTest.getId())).isNull();
    }
}