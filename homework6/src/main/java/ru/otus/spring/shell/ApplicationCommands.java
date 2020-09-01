package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.*;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private String userName;
    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;
    private final CommentService commentService;

    //----------- Authors

    @ShellMethod(value = "Create author in table", key = {"ca", "create author"})
    public void createAuthor(String fullName) {
        authorService.saveAuthor(new Author(null, fullName, null));
    }

    @ShellMethod(value = "Read all authors", key = {"ra", "authors table read"})
    public String readAuthors()
    {
        String resAuthor = "";
        for(Author item : authorService.readTable())
            resAuthor += item.getId() + " " + item.getName() + " \n ";

        return resAuthor;
    }

    @ShellMethod(value = "Update author in table", key = {"ua", "update author"})
    public void updateAuthor(long id, String fullName) {
        authorService.saveAuthor(new Author(id, fullName, null));
    }

    @ShellMethod(value = "Delete author in table by id", key = {"da", "delete author id"})
    public void deleteAuthorById(long id) {
        authorService.deleteById(id);
    }

    //----------- Books

    @ShellMethod(value = "Create book in table", key = {"cb", "create book"})
    public void createBook(String bookName, String genreName, String authorFullName) {
        bookService.saveBook( new Book(
                null,
                bookName,
                new Genre(null, genreName),
                new Author(null, authorFullName, null))
        );
    }

    @ShellMethod(value = "Read all books", key = {"rb", "book table read"})
    public String readBook() {

        String resBook = "";
        for(Book item : bookService.readTable())
            resBook += item.getId() + " " + item.getName() + " " + item.getGenre().getName() + " " + item.getAuthor().getName() + " \n";

        return resBook;
    }

    @ShellMethod(value = "Read books by author", key = {"rba", "book table read by author"})
    public String readBookByAuthor(String authorFullName) {

        String resBook = "";
        for(Book item : bookService.readTableByAuthor(new Author(null, authorFullName, null)))
            resBook += item.getId() + " " + item.getName() + " " + item.getGenre().getName() + " " + item.getAuthor().getName() + " \n";

        return resBook;
    }

    /*@ShellMethod(value = "Read books by id author", key = {"rba", "book table read by author id"})
    public String readBookByID(Long authorId) {

        String resBook = "";
        for(Book item : bookService.readTableByAuthor(new Author(authorId, null, null)))
            resBook += item.getId() + " " + item.getName() + " " + item.getGenre().getName() + " " + item.getAuthor().getName() + " \n";

        return resBook;
    }*/

    @ShellMethod(value = "Update book in table", key = {"ub", "update book"})
    public void updateBook(long id, String bookName, String genreName, String authorFullName) {
        bookService.saveBook( new Book(
                id,
                bookName,
                new Genre(null, genreName),
                new Author(null, authorFullName, null))
        );
    }

    @ShellMethod(value = "Delete book in table by id", key = {"db", "delete book id"})
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }


    //----------- Genres

    @ShellMethod(value = "Create genre in table", key = {"cg", "create genre"})
    public void createGenre(String name) {
        genreService.saveGenre(new Genre(null, name));
    }

    @ShellMethod(value = "Read all genres", key = {"rg", "genre table read"})
    public String readGenres() {

        String resGenre = "";
        for(Genre item : genreService.readTable())
            resGenre += item.getId() + " " + item.getName() + " \n ";

        return resGenre;
    }

    @ShellMethod(value = "Update genre in table", key = {"ug", "update genre"})
    public void updateGenre(long id, String fullName) {
        genreService.saveGenre(new Genre(id, fullName));
    }

    @ShellMethod(value = "Delete genre in table by id", key = {"dg", "delete genre id"})
    public void deleteGenreById(long id) {
        genreService.deleteById(id);
    }
    
    //---------- Comments

    @ShellMethod(value = "Create comment", key = { "cc", "create comment"})
    public void add(Long bookId, String comment) {
        commentService.createComment(bookId, comment);
    }

    @ShellMethod(value = "Read comments", key = {"rc", "read all comments"})
    public String list() {

        String resComment = "";
        for(Comment item : commentService.readTable())
            resComment += item.getId() + " " + item.getComment() + " \n";

        return resComment;
    }

    @ShellMethod(value = "Update comment", key = {"uc", "update comment"})
    public void updateComment(long id, String comment) {
        commentService.updateComment(id, comment);
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "delete comment"})
    public void delete(long id) {
        commentService.deleteById(id);
    }
}
