package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.domain.Book;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepo;

    @Transactional
    @Override
    public void saveBook(Book book) {
        bookRepo.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> readTable() { return bookRepo.findAll();}

    @Transactional(readOnly = true)
    @Override
    public List<Book> readTableByAuthor(Author author) {

        List<Book> books = bookRepo.findAll();
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (!author.getName().equals(iterator.next().getAuthor().getName()))
                iterator.remove();
        }
        return books;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }
}
