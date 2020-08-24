package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.AuthorRepositoryJpa;
import ru.otus.spring.domain.Author;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa authorRepo;

    @Transactional
    @Override
    public void saveAuthor(Author author)
    {
        authorRepo.insertByAuthor(author);
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
}
