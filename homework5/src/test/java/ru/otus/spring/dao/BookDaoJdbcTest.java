package ru.otus.spring.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookOnly;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование BookDaoJdbcTest")
@JdbcTest
@Import(TestDaoConfiguration.class)
public class BookDaoJdbcTest {

    @Autowired
    BookDao bookDao;

    @DisplayName("Создание книги")
    @Test
    void insertBook() {

        BookOnly bookTest = new BookOnly("Заповедник гоблинов","фантастика", "Клиффорд Саймак");
        long newID = bookDao.insertBookByParams(bookTest);

        assertThat(getBookOnly(bookDao.getById(newID).get()))
                .isEqualToComparingFieldByField(bookTest);
    }

    @DisplayName("Изменение книги")
    @Test
    void updateBook() {

        BookOnly firstBook = new BookOnly("Пикник на обочине","фантастика", "Браться Стругатские");
        BookOnly secondBook = new BookOnly("Вино из одуванчиков","фантастика", "Рей Бредбери");
        long newID = bookDao.insertBookByParams(firstBook);

        assertThat(getBookOnly(bookDao.getById(newID).get()))
                .isEqualToComparingFieldByField(firstBook);

        Optional<Book> foundOldBook = bookDao.getByParams(firstBook);
        if(!foundOldBook.isEmpty()){
            long bookForUpdateID = foundOldBook.get().getId();
            bookDao.deleteBookById(bookForUpdateID);
            bookDao.insertById(bookForUpdateID, secondBook);
        }

        assertThat(getBookOnly(bookDao.getById(newID).get()))
                .isEqualToComparingFieldByField(secondBook);
    }

    /*@DisplayName("Изменить несуществующую книгу")
    @Test
    void updateError() {
        BookOnly firstBook = new BookOnly("Хроники Марса","фантастика", "Рей Бредбери");
        book.setId(1L);

        Assertions.assertThatThrownBy(() -> bookDao.save(book))
                .isInstanceOf(EntityNotFound.class);
    }*/

    @DisplayName("Удалить книгу по идентификатору")
    @Test
    void deleteById() {

        BookOnly firstBook = new BookOnly("Марсианские хроники","фантастика", "Рей Бредбери");
        long newID = bookDao.insertBookByParams(firstBook);
        bookDao.deleteBookById(newID);
        Optional<Book> foundBook = bookDao.getById(newID);

        assertThat(foundBook).isEmpty();
    }

    @DisplayName("Удалить все книги")
    @Test
    void deleteAll() {

        BookOnly firstBook = new BookOnly("451 градус по Фаренгейту","фантастика", "Рей Бредбери");
        BookOnly secondBook = new BookOnly("Лекарство от меланхолии","фантастика", "Рей Бредбери");
        BookOnly thirdBook = new BookOnly("Левиафан-99","фантастика", "Рей Бредбери");

        long firstBookID = bookDao.insertBookByParams(firstBook);
        long secondBookID = bookDao.insertBookByParams(secondBook);
        long thirdBookID = bookDao.insertBookByParams(thirdBook);

        assertThat(getBookOnly(bookDao.getById(firstBookID).get())).isEqualToComparingFieldByField(firstBook);
        assertThat(getBookOnly(bookDao.getById(secondBookID).get())).isEqualToComparingFieldByField(secondBook);
        assertThat(getBookOnly(bookDao.getById(thirdBookID).get())).isEqualToComparingFieldByField(thirdBook);

        bookDao.deleteAll();

        assertThat(bookDao.getById(firstBookID)).isEmpty();
        assertThat(bookDao.getById(secondBookID)).isEmpty();
        assertThat(bookDao.getById(thirdBookID)).isEmpty();
    }

    @DisplayName("Найти все книги")
    @Test
    void findAll() {
        BookOnly firstBook = new BookOnly("Терминус","фантастика", "Станислав Лем");
        BookOnly secondBook = new BookOnly("Планета смерти","фантастика", "Гарри Гаррисон");

        long firstBookID = bookDao.insertBookByParams(firstBook);
        long secondBookID = bookDao.insertBookByParams(secondBook);

        bookDao.getAll();

        Assertions.assertThat(bookDao.getAll())
                .contains(bookDao.getById(firstBookID).get(), bookDao.getById(secondBookID).get());
    }

    @DisplayName("Найти книгу по имени")
    @Test
    void findByName() {

        BookOnly firstBook = new BookOnly("Как потерялся робот", "фантастика", "Айзек Азимов");
        long firstBookID = bookDao.insertBookByParams(firstBook);
        Optional<Book> foundBook = bookDao.getByName(firstBook.getBookName());

        Assertions.assertThat(getBookOnly(foundBook.get()))
                .isEqualTo(firstBook);
    }

    @DisplayName("Найти книгу по идентификатору")
    @Test
    void findById() {

        BookOnly firstBook = new BookOnly("Понедельник начинается в субботу", "фантастика", "Братья Стругацкие");
        long firstBookID = bookDao.insertBookByParams(firstBook);
        Optional<Book> foundBook = bookDao.getById(firstBookID);

        Assertions.assertThat(getBookOnly(foundBook.get()))
                .isEqualTo(firstBook);
    }

    public BookOnly getBookOnly(Book book) {

        long bookId = book.getId();
        String bookName =  book.getName();
        String genreName = book.getGenre().getName();
        String authorName = book.getAuthor().getName();

        return new BookOnly(bookName, genreName, authorName);
    }
}