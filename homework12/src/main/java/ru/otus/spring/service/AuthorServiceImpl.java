package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;

    @Transactional
    @Override
    public void saveAuthor(Author author)
    {
        authorRepo.save(author);
    };

    @Transactional
    @Override
    public String readTable(){

        String resAuthor = "";
        for(Author item : authorRepo.findAll())
            resAuthor += item.getId() + " " + item.getName() + " \n ";

        return resAuthor;
    };

    @Transactional
    @Override
    public void deleteById(long id){
        authorRepo.deleteById(id);
    };

    @Transactional(readOnly = true)
    @Override
    public Author findAuthorsByBookId(Long bookId) {

        Book book = bookRepo.findById(bookId).get();

        //Author result1 = book.getAuthor();

        /*List<Author> result =
                StreamSupport.stream(authorRepo.findAll().spliterator(), false)
                        .collect(Collectors.toList());*/
        return book.getAuthor();
    }

    /*@Transactional(readOnly = true)
    @Override
    public Author getAuthorById(long id) {

        return authorRepo.findById(id).get();
    }*/
}
