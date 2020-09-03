package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.domain.Book;

import java.util.ArrayList;
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

    @Override
    public List<Book> readTable() { return bookRepo.findAll();}

    @Override
    public List<Book> readTableByAuthors(List<Author> authors) {

        List<Book> resultBookList = new ArrayList<Book>();

        for(Author author : authors) {
            resultBookList.addAll(author.getBooks());
        }

        return resultBookList;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }
}
