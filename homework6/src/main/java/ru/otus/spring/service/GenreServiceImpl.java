package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.GenreRepositoryJpa;
import ru.otus.spring.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositoryJpa genreRepo;

    @Transactional
    @Override
    public void saveGenre(Genre genre)
    {
        genreRepo.insertByGenre(genre);
    };

    @Transactional
    @Override
    public String readTable(){

        String resGenre = "";
        for(Genre item : genreRepo.findAll())
            resGenre += item.getId() + " " + item.getName() + " \n ";

        return resGenre;
    };

    @Transactional
    @Override
    public void deleteById(long id){
        genreRepo.deleteById(id);
    };
}
