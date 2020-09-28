package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
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
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void createAuthor(String fullName) {
        authorService.saveAuthor(new Author(null, fullName));
    }

    @ShellMethod(value = "Read all authors", key = {"ra", "authors table read"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readAuthors() {
        return authorService.readTable();
    }

    @ShellMethod(value = "Update author in table", key = {"ua", "update author"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void updateAuthor(long id, String fullName) {
        authorService.saveAuthor(new Author(id, fullName));
    }

    @ShellMethod(value = "Delete author in table by id", key = {"da", "delete author id"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void deleteAuthorById(long id) {
        authorService.deleteById(id);
    }

    //----------- Books

    @ShellMethod(value = "Create book in table", key = {"cb", "create book"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void createBook(String bookName, String genreName, String authorFullName) {
        bookService.saveBook( new Book(
                null,
                bookName,
                new Genre(null, genreName),
                new Author(null, authorFullName))
        );
    }

    @ShellMethod(value = "Read all books", key = {"rb", "book table read"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readBook() {
        return bookService.readTable();
    }

    @ShellMethod(value = "Update book in table", key = {"ub", "update book"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void updateBook(long id, String bookName, String genreName, String authorFullName) {
        bookService.saveBook( new Book(
                id,
                bookName,
                new Genre(null, genreName),
                new Author(null, authorFullName))
        );
    }

    @ShellMethod(value = "Delete book in table by id", key = {"db", "delete book id"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }


    //----------- Genres

    @ShellMethod(value = "Create genre in table", key = {"cg", "create genre"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void createGenre(String name) {
        genreService.saveGenre(new Genre(null, name));
    }

    @ShellMethod(value = "Read all genres", key = {"rg", "genre table read"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readGenres() {
        return genreService.readTable();
    }

    @ShellMethod(value = "Update genre in table", key = {"ug", "update genre"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void updateGenre(long id, String fullName) {
        genreService.saveGenre(new Genre(id, fullName));
    }

    @ShellMethod(value = "Delete genre in table by id", key = {"dg", "delete genre id"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void deleteGenreById(long id) {
        genreService.deleteById(id);
    }
    
    //---------- Comments

    @ShellMethod(value = "Create comment", key = { "cc", "create comment"})
    public void add(Long bookId, String comment) {
        commentService.createComment(bookId, comment);
    }

    @ShellMethod(value = "Read comments", key = {"rc", "read all comments"})
    public void list() {
        commentService.readTable();
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
