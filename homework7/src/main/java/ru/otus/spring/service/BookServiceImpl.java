package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.domain.Book;

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

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }
}
