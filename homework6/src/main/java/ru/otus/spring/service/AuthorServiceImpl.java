package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.AuthorRepositoryJpa;
import ru.otus.spring.domain.Author;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa authorRepo;

    @Transactional
    @Override
    public void saveAuthor(Author author)
    {
        authorRepo.save(author);
    };

    @Transactional(readOnly = true)
    @Override
    public List<Author> readTable(){
        return authorRepo.findAll();
    };

    @Transactional
    @Override
    public void deleteById(long id){
        authorRepo.deleteById(id);
    };
}
