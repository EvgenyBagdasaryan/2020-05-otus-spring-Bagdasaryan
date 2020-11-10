package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepo;

    @Transactional
    @Override
    public void saveGenre(Genre genre)
    {
        genreRepo.save(genre);
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
