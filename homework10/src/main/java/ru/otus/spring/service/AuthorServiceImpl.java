package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void saveAuthor(String bookId, Author author) {

        authorRepository.add(bookId, author);
    }

    @Override
    public List<Author> getAuthorsByBookId(String bookId) {

        String resAuthor = "";
        for(Author item : authorRepository.findAuthorsByBookId(bookId))
            resAuthor += item.getId() + " " + item.getName() + " \n ";

        return authorRepository.findAuthorsByBookId(bookId);
    }

    @Override
    public Author findByAuthorId(String authorId) throws DocumentNotFoundException {
        return authorRepository.findByAuthorId(authorId);
    }

    @Override
    public void updateAuthor(String id, Author author) {
        authorRepository.update(id, author);
    }

    @Override
    public void deleteById(String id) throws DocumentNotFoundException {
        authorRepository.deleteByAuthorId(id);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }
}