package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @Override
    public void saveAuthorByFullName(String fullName)
    {
        authorDao.insertByFullName(fullName);
    };
    @Override
    public String readTable(){

        String resAuthor = "";
        for(Author item : authorDao.getAll())
            resAuthor += item.getId() + " " + item.getName() + " \n ";

        return resAuthor;
    };

    @Override
    public void updateAuthorByFullName(String fullNameOld, String fullNameNew){
        authorDao.updateAuthor(fullNameOld, fullNameNew);
    };

    @Override
    public String deleteAuthorByFullName(String fullName){

        String result = "";
        if(bookDao.getByAuthorName(fullName).size() == 0) {
            if(!authorDao.getByFullName(fullName).isEmpty()){
                authorDao.deleteByFullName(fullName);
                result = "Автор удален";
            }else{
                result = "Такого автора нет";
            }
        }else{
            result = "Автор не может быть удален, есть книги с его авторством";
        }
        return result;
    };
}
