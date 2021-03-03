package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {

        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String bookId) throws DocumentNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(() -> new DocumentNotFoundException(bookId, Book.class.getName()));
    }

    @Override
    public void deleteById(String id) throws DocumentNotFoundException {
        bookRepository.deleteById(id);
    };

    @Override
    public List<Genre> getGenresBook(String bookId) throws DocumentNotFoundException {
        return getBookById(bookId).getGenres();
    }

    @Override
    public List<Comment> getCommentsBook(String bookId) throws DocumentNotFoundException {
        return getBookById(bookId).getComments();
    }

    @Override
    public void updateNameById(String id, String newName) {

        bookRepository.updateBookNameById(id, newName);
    }
}