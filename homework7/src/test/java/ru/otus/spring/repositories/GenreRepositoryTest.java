package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование GenreRepositoryJpaTest")
@DataJpaTest
@Import(TestConfigurationJpa.class)
public class GenreRepositoryTest {

    @Autowired
    GenreRepository genreRepo;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Создать жанр")
    @Test
    void createGenre() {
        Genre genreTest = new Genre(null, "триллер");
        genreRepo.save(genreTest);

        assertThat(em.find(Genre.class, genreTest.getId()))
                .isEqualToComparingFieldByField(genreTest);
    }

    @DisplayName("Изменение жанра")
    @Test
    void updateGenre() {
        Genre genreTest = new Genre(null, "фантастика");
        em.persist(genreTest);

        genreTest.setName("Genre New");
        genreRepo.save(genreTest);

        assertThat(em.find(Genre.class, genreTest.getId()))
                .isEqualToComparingFieldByField(genreTest);
    }

    @DisplayName("Найти все жанры")
    @Test
    void findAll() {
        Genre genreTest1 = new Genre(null, "триллер");
        Genre genreTest2 = new Genre(null, "фантастика");

        em.persist(genreTest1);
        em.persist(genreTest2);

        assertThat(genreRepo.findAll())
                .hasSize(2)
                .containsOnly(genreTest1, genreTest2);
    }

    @DisplayName("Найти жанр по идентификатору")
    @Test
    void findById() {
        Genre genreTest1 = new Genre(null, "триллер");
        em.persist(genreTest1);

        assertThat(genreRepo.findById(genreTest1.getId()))
                .get()
                .isEqualToComparingFieldByField(genreTest1);
    }

    @DisplayName("Удалить жанр по идентификатору")
    @Test
    void deleteById() {
        Genre genreTest1 = new Genre(null, "фантастика");

        em.persist(genreTest1);

        em.detach(genreTest1);

        genreRepo.deleteById(genreTest1.getId());

        Genre genreTest2 = em.find(Genre.class, genreTest1.getId());

        assertThat(genreTest2).isNull();
    }
}