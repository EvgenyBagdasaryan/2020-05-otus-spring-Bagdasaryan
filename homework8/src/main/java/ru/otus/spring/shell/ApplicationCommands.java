package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.domain.Author;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;
    private final CommentService commentService;

    //----------- Authors

    @ShellMethod(value = "Create author in table", key = {"ca", "create author"})
    public void createAuthor(String fullName) {
        authorService.saveAuthor(new Author(null, fullName));
    }

    @ShellMethod(value = "Read all authors", key = {"ra", "authors table read"})
    public List<Author> readAuthors() {
        return authorService.getAuthors();
    }

    @ShellMethod(value = "Update author in table", key = {"ua", "update author"})
    public void updateAuthor(String id, String fullName) {
        authorService.saveAuthor(new Author(id, fullName));
    }

    @ShellMethod(value = "Delete author in table by id", key = {"da", "delete author id"})
    public void deleteAuthorById(String id) throws DocumentNotFoundException {
        authorService.deleteById(id);
    }

    //----------- Books

    @ShellMethod(value = "Read all books", key = {"rb", "book table read"})
    public List<Book> readBook() {
        return bookService.getBooks();
    }

    /*@ShellMethod(value = "Read all books by genre name", key = {"rbgn", "Read all books by genre name"})
    public List<Book> readBookByGenreName(String genreName)  throws DocumentNotFoundException{
        return bookService.findByGenreName(genreName);
    }*/

    @ShellMethod(value = "Delete book in table by id", key = {"db", "delete book id"})
    public void deleteBookById(String id) throws DocumentNotFoundException {
        bookService.deleteById(id);
    }

    //----------- Genres

    @ShellMethod(value = "Create genre in table", key = {"cg", "create genre"})
    public void createGenre(String name) { genreService.saveGenre(new Genre(null, name)); }

    @ShellMethod(value = "Read all genres", key = {"rg", "genre table read"})
    public List<Genre> readGenres() { return genreService.getGenres(); }

    @ShellMethod(value = "Read all genres by book id", key = {"rgbi", "Read all genres by book id"})
    public List<Genre> readGenresByBookId(String bookID)  throws DocumentNotFoundException{
        return bookService.getGenresBook(bookID);
    }

    @ShellMethod(value = "Update genre in table", key = {"ug", "update genre"})
    public void updateGenre(String id, String fullName) { genreService.saveGenre(new Genre(id, fullName)); }

    @ShellMethod(value = "Delete genre in table by id", key = {"dg", "delete genre id"})
    public void deleteGenreById(String id) throws DocumentNotFoundException  { genreService.deleteById(id); }
    
    //---------- Comments

    @ShellMethod(value = "Create comment", key = { "cc", "create comment"})
    public void add(String comment) {
        commentService.saveComment(new Comment(null, comment));
    }

    @ShellMethod(value = "Read comments", key = {"rc", "read all comments"})
    public List<Comment> readComments() {
        return commentService.getComments();
    }

    @ShellMethod(value = "Read comments by book ID", key = {"rcbi", "Read comments by book ID"})
    public List<Comment> readCommentsByBookId(String bookId) {
        return commentService.getCommentsByBookID(bookId);
    }

    @ShellMethod(value = "Update comment", key = {"uc", "update comment"})
    public void updateComment(String id, String comment) {
        commentService.saveComment(new Comment(id, comment));
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "delete comment"})
    public void delete(String id)  throws DocumentNotFoundException {
        commentService.deleteComment(id);
    }
}