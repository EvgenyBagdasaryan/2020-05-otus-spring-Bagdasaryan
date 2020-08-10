package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookOnly;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@DisplayName("Тест сервиса BookServiceImpl")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private IOService ioService;

    @MockBean
    private BookDao bookDao;

    @DisplayName("Создать книгу")
    @Test
    void save() {
        BookOnly firstBook = new BookOnly("Страна багровых туч","фантастика", "Братья Стругацкие");

        bookService.createBook(firstBook);

        verify(bookDao).insertBookByParams(firstBook);
    }

    @DisplayName("Найти книгу")
    @Test
    void findByParam() {

        BookOnly findBook = new BookOnly("Страна багровых туч","фантастика", "Братья Стругацкие");
        Optional<Book> book = bookService.readBookByParam(findBook);

        verify(bookDao).getByParams(findBook);
    }
    @DisplayName("Удалить книгу по параметрам")
    @Test
    void deleteByParam() {

        BookOnly bookForDelete = new BookOnly("Трудно быть богом","фантастика", "Братья Стругацкие");
        Optional<Book> book = bookService.readBookByParam(bookForDelete);
        if(!book.isEmpty()){
            long id = book.get().getId();
            bookService.deleteBook(bookForDelete);
            verify(bookDao).deleteBookById(id);
        }
    }
}