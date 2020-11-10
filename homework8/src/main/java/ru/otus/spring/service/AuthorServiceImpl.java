package ru.otus.spring.service;

import lombok.val;
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
    public void saveAuthor(Author author) {

        authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthors() {

        String resAuthor = "";
        for(Author item : authorRepository.findAll())
            resAuthor += item.getId() + " " + item.getName() + " \n ";

        return authorRepository.findAll();
    }

    @Override
    public void deleteById(String id) throws DocumentNotFoundException {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id, Author.class.getName()));

        authorRepository.deleteById(id);
    };
}