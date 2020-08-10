package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final BookDao bookDao;

    @Override
    public void saveGenreByName(String name)
    {
        genreDao.insertByName(name);
    };

    @Override
    public String readTableGenres(){

        String resGenre = "";
        for(Genre item : genreDao.getAll())
            resGenre += item.getId() + " " + item.getName() + "   ";

        return resGenre;
    };

    @Override
    public void updateGenreByName(String nameOld, String nameNew){

        genreDao.updateGenre(nameOld, nameNew);
    };

    @Override
    public String deleteGenreByName(String fullName){

        String result = "";
        if(bookDao.getByAuthorName(fullName).size() == 0) {
            if(!genreDao.getByName(fullName).isEmpty()){
                genreDao.deleteByName(fullName);
                result = "Жанр удален";
            }else{
                result = "Такого жанра нет";
            }
        }else{
            result = "Жанр не может быть удален, есть книги такого жанра";
        }
        return result;
    };
}
