package ru.otus.spring;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class TestData {

    public static final Author author1 = new Author("1", "Чарлз Диккенс");
    public static final Author author2 = new Author("2", "Джек Лондон");

    public static final Genre genre1 = new Genre("1", "классика");
    public static final Genre genre2 = new Genre("2", "проза");

    public static final Comment comment1 = new Comment("1", "Текст комментария 1");
    public static final Comment comment2 = new Comment("2", "Текст комментария 2");

    public static final Book bookDikkens = new Book("1", "Оливер Твист",
            List.of(genre1),
            List.of(author1),
            List.of(comment1));
    public static final Book bookLondon = new Book("2", "Рассказы Севера",
            List.of(genre2),
            List.of(author2),
            List.of(comment2));
    public static final Book bookLondon2 = new Book("3", "Любовь к жизни",
            List.of(genre2),
            List.of(author2),
            List.of(comment2));

    public static final int IDIOT_COMMENTS_COUNT = 2;

    public static Set<Author> getAuthors(){
        return Set.of(author1, author2);
    }
    public static Set<Genre> getGenres(){
        return Set.of(genre1, genre2);
    }
    public static Set<Book> getBooks(){
        return Set.of(bookDikkens, bookLondon, bookLondon2);
    }

    public static Set<Comment> getComments() {
        return Set.of(comment1, comment2);
    }
}
