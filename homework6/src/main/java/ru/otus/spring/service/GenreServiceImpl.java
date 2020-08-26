package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.GenreRepositoryJpa;
import ru.otus.spring.domain.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositoryJpa genreRepo;

    @Transactional
    @Override
    public void saveGenre(Genre genre)
    {
        genreRepo.save(genre);
    };

    @Transactional(readOnly = true)
    @Override
    public List<Genre> readTable(){
        return genreRepo.findAll();
    };

    @Transactional
    @Override
    public void deleteById(long id){
        genreRepo.deleteById(id);
    };
}
