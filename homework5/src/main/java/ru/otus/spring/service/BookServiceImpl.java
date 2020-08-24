package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookOnly;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public void createBook(BookOnly book){

        Optional<Book> foundBook = bookDao.getByParams(book);
        if(foundBook.isEmpty()) {
            bookDao.insertBookByParams(book);
        }
    };

    public String readBooksTable(){

        String allBooks = "";
        List<Book> tmp = bookDao.getAll();
        for(Book item : bookDao.getAll()){

            BookOnly book = getBookOnly(item);
            allBooks += "id: " + item.getId() + " Название книги: " + book.getBookName() + " Жанр: " + book.getGenreName() + " Автор: " + book.getAuthorName() + "\n";
        }

        return allBooks;
    };

    public String readBook(BookOnly book){

        String rez = "не нашел!";
        Optional<Book> foundBook = bookDao.getByParams(book);
        if(!foundBook.isEmpty()) {
            rez = foundBook.get().getId() + " " + foundBook.get().getName();
        }

        return rez;
    };

    public String readBookByBookName(String bookName){

        String rez = "не нашел!";
        Optional <Book> foundBook = bookDao.getByName(bookName);
        if(!foundBook.isEmpty()) {
            rez = foundBook.get().getId() + " " + foundBook.get().getName();
        }
        return rez;
    };

    public String readBookById(long id){

        String rez = "не нашел!";
        Optional<Book> foundBook = bookDao.getById(id);
        if(!foundBook.isEmpty()) {
            rez = foundBook.get().getId() + " " + foundBook.get().getName();
        }

        return rez;
    };

    public Optional<Book> readBookByParam(BookOnly book){

        return bookDao.getByParams(book);
    };

    public void updateBook(BookOnly bookOld, BookOnly bookNew){

        Optional<Book> foundBook = bookDao.getByParams(bookOld);
        if(foundBook.isEmpty()) {
            bookDao.insertBookByParams(bookNew);
        }else{
            long bookForUpdateID = foundBook.get().getId();
            bookDao.deleteBookById(bookForUpdateID);
            bookDao.insertById(bookForUpdateID, bookNew);
        }
    };

    public void updateBookById(long id, BookOnly bookNew){

        Optional<Book> foundBook = bookDao.getById(id);
        if(foundBook.isEmpty()) {
            bookDao.insertBookByParams(bookNew);
        }else{
            bookDao.deleteBookById(id);
            bookDao.insertById(id, bookNew);
        }
    };

    public void deleteBook(BookOnly book){

        Optional<Book> foundBook = bookDao.getByParams(book);
        if(!foundBook.isEmpty()) {
            long bookForUpdateID = foundBook.get().getId();
            bookDao.deleteBookById(bookForUpdateID);
        }
    };

    public void deleteBookById(long Id){

        Optional<Book> foundBook = bookDao.getById(Id);
        if(!foundBook.isEmpty()) {
            bookDao.deleteBookById(Id);
        }
    };

    public BookOnly getBookOnly(Book book) {

        long bookId = book.getId();
        String bookName =  book.getName();
        String genreName = book.getGenre().getName();
        String authorName = book.getAuthor().getName();

        return new BookOnly(bookName, genreName, authorName);
    }
}
