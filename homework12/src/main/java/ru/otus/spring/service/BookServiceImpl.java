package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;

    @Transactional
    @Override
    public void saveBook(Book book) {
        bookRepo.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public String readTable() {
        String resBook = "";
        for(Book item : bookRepo.findAll())
            resBook += item.getId() + " " + item.getName() + " " + item.getGenre().getName() + " " + item.getAuthor().getName() + " \n";

        return resBook;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooks() {

        List<Book> result =
                StreamSupport.stream(bookRepo.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(long id) {

        return bookRepo.findById(id).get();
    }

    @Transactional
    @Override
    public void updateNameById(long id, String name) {

        Book oldBook = bookRepo.findById(id).get();
        oldBook.setName(name);
        bookRepo.save(oldBook);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }
}