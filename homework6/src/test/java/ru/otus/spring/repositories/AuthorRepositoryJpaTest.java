package ru.otus.spring.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование AuthorRepositoryJpaTest")
@DataJpaTest
@Import(TestJpaConfiguration.class)
public class AuthorRepositoryJpaTest {

    @Autowired
    AuthorRepositoryJpa authorRepo;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Создать автора")
    @Test
    void createAuthor() {
        Author testAuthor = new Author(null, "Клиффорд Саймак");
        authorRepo.save(testAuthor);
        assertThat(em.find(Author.class, testAuthor.getId())).isEqualToComparingFieldByField(testAuthor);
    }

    @DisplayName("Обновление автора")
    @Test
    void updateAuthor() {
        Author testAuthor = new Author(null, "Клиффорд Саймак");
        em.persist(testAuthor);

        testAuthor.setName("Андрэ Нортон");
        authorRepo.save(testAuthor);

        assertThat(em.find(Author.class, testAuthor.getId())).isEqualToComparingFieldByField(testAuthor);
    }

    @DisplayName("Найти всех авторов")
    @Test
    void getAll() {
        Author testAuthor1 = new Author(null, "Клиффорд Саймак");
        Author testAuthor2 = new Author(null, "Андрэ Нортон");

        em.persist(testAuthor1);
        em.persist(testAuthor2);

        Assertions.assertThat(authorRepo.findAll()).containsOnly(testAuthor1, testAuthor2);
    }
}