package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.repositories.BookRepository;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public List<Genre> getGenres() {

        String resGenre = "";
        for(Genre item : genreRepository.findAll())
            resGenre += item.getId() + " " + item.getName() + " \n ";

        return genreRepository.findAll();
    }

    @Override
    public List<Genre> getGenresByBookID(String bookId){

        List<Genre> foundGenres  = bookRepository.findGenresByBookId(bookId);

        String resGenres = "";
        for (Genre item : foundGenres)
            resGenres += item.getId() + " " + item.getName() + " \n ";

        return foundGenres;
    }

    @Override
    public void deleteById(String id) throws DocumentNotFoundException {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id, Genre.class.getName()));

        genreRepository.deleteById(id);
    };
}
