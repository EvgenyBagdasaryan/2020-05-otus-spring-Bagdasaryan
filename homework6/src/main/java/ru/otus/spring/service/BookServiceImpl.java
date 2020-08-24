package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.domain.Book;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepo;

    @Transactional
    @Override
    public void saveBook(Book book) {
        bookRepo.insertByBook(book);
    }

    @Transactional(readOnly = true)
    @Override
    public String readTable() {
        String resBook = "";
        for(Book item : bookRepo.findAll())
            resBook += item.getId() + " " + item.getName() + " " + item.getGenre().getName() + " " + item.getAuthor().getName() + " \n";

        return resBook;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }
}
