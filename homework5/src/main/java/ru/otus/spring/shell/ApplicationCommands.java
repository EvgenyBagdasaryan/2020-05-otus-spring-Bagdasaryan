package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.BookOnly;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private String userName;
    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;

    //----------- Authors

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Create author in table by full name", key = {"ca", "save author by full name"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void createAuthor(String fullName) {
        authorService.saveAuthorByFullName(fullName);
    }

    @ShellMethod(value = "Read all authors", key = {"ra", "authors table read"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readAuthors() {
        return authorService.readTable();
    }

    @ShellMethod(value = "Update author in table by full name", key = {"ua", "update author by full name"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void updateAuthor(String fullNameOld, String fullNameNew) {
        authorService.updateAuthorByFullName(fullNameOld, fullNameNew);
    }

    @ShellMethod(value = "Delete author in table by full name", key = {"da", "delete author by full name"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void deleteAuthorByFullName(String fullName) {
        //authorService.deleteAuthorByFullName(fullName);
    }

    //----------- Books

    @ShellMethod(value = "Create book in table", key = {"cb", "create book"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void createBook(String bookName, String bookGenre, String authorFullName) {
        BookOnly createBook = new BookOnly(bookName, bookGenre, authorFullName);
        bookService.createBook(createBook);
    }

    @ShellMethod(value = "Read all books", key = {"rba", "all books table read"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readBooks() {
        return bookService.readBooksTable();
    }

    @ShellMethod(value = "Read book by name", key = {"rbn", "read book by name"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readBookByName(String bookName) {
        return String.format("Результат: %s", bookService.readBookByBookName(bookName));
    }

    @ShellMethod(value = "Read book by id", key = {"rbi", "read book by id"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readBookById(long id) {
        return String.format("Результат по Id: %s", bookService.readBookById(id));
    }

    @ShellMethod(value = "Update book by id", key = {"ub", "update book by id"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void updateBook(long id, String bookName, String bookGenre, String authorFullName) {
        bookService.updateBookById(id, new BookOnly(bookName, bookGenre, authorFullName));
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete book"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void deleteBookByID(long bookID) {
        bookService.deleteBookById(bookID);
    }

    private Availability isCommandAvailable() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }

    //----------- Genres

    @ShellMethod(value = "Create genre in table by name", key = {"cg", "sCreate genre by name"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void createGenre(String name) {
        genreService.saveGenreByName(name);
    }

    @ShellMethod(value = "Read all genres", key = {"rg", "genres table read"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String readGenres() {
        return genreService.readTableGenres();
    }

    @ShellMethod(value = "Update genre in table by name", key = {"ua", "update genre by name"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void updateGenre(String nameOld, String nameNew) {
        genreService.updateGenreByName(nameOld, nameNew);
    }

    @ShellMethod(value = "Delete genre in table by name", key = {"da", "delete genre by name"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void deleteGenreByName(String name) {
        genreService.deleteGenreByName(name);
    }
}
