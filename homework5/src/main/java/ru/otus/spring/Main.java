package ru.otus.spring;

import lombok.RequiredArgsConstructor;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.AuthorService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Main.class, args);

        //SpringApplication.run(SpringStudentCheck4Application.class);

        /*AuthorDao daoAuthor = context.getBean(AuthorDao.class);
        System.out.println("All count of authors table: " + daoAuthor.count());
        //dao1.insert(new Author(2, "ivan"));
        //System.out.println("All count " + dao1.count());
        Author auth = daoAuthor.getById(2);
        System.out.println("Author id: " + auth.getId() + " name: " + auth.getName());
        System.out.println(daoAuthor.getAll());*/

        /*BookDao daoBook = context.getBean(BookDao.class);
        System.out.println("All count of books table: " + daoBook.count());
        //dao1.insert(new Book(2, "ivan"));
        //System.out.println("All count " + dao1.count());
        //Book boo = daoBook.getById(2);
        //System.out.println("Book id: " + boo.getId() + " name: " + boo.getName());
        System.out.println(daoBook.getAll());*/

        //AuthorService authServ = context.getBean(AuthorService.class);
        //authServ.readTable();

        //Console.main(args);
    }
}
