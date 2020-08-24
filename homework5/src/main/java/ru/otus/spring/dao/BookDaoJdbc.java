package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookOnly;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao  {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insertById(long id, BookOnly book) {

        authorDao.insertByFullName(book.getAuthorName());
        genreDao.insertByName(book.getGenreName());

        long genreId = genreDao.getByName(book.getGenreName()).get().getId();
        long authorId = authorDao.getByFullName(book.getAuthorName()).get().getId();

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("bookName", book.getBookName())
                .addValue("genre_id", genreId)
                .addValue("author_id", authorId);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into books (id, name, genre_id, author_id) values (:id, :bookName, :genre_id, :author_id)", parameterSource, keyHolder);

        long bookId = Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long insertBookByParams(BookOnly book) {

        authorDao.insertByFullName(book.getAuthorName());
        genreDao.insertByName(book.getGenreName());

        long genreId = genreDao.getByName(book.getGenreName()).get().getId();
        long authorId = authorDao.getByFullName(book.getAuthorName()).get().getId();

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("bookName", book.getBookName())
                .addValue("genre_id", genreId)
                .addValue("author_id", authorId);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into books (name, genre_id, author_id) values (:bookName, :genre_id, :author_id)", parameterSource, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<Book> getByName(String name) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", name);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        List<Book> books = namedParameterJdbcOperations.query(
                "select " +
                        "bk.id as book_id, " +
                        "bk.name as book_name, " +
                        "at.id as author_id, " +
                        "at.name as author_name, " +
                        "gn.id as genre_id, " +
                        "gn.name as genre_name " +
                        "from books bk " +
                        "inner join genres gn on bk.genre_id = gn.id " +
                        "inner join authors at on bk.author_id = at.id where bk.name = :name",
                parameterSource, new BookDaoJdbc.BookMapper());

        return books.stream().findFirst();
    }

    @Override
    public Optional<Book> getByParams(BookOnly book) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("bookName", book.getBookName())
                .addValue("bookGenre", book.getGenreName())
                .addValue("bookAuthor", book.getAuthorName());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        List<Book> books = namedParameterJdbcOperations.query(
                "select " +
                        "bk.id as book_id, " +
                        "bk.name as book_name, " +
                        "at.id as author_id, " +
                        "at.name as author_name, " +
                        "gn.id as genre_id, " +
                        "gn.name as genre_name " +
                        "from books bk " +
                        "inner join genres gn on bk.genre_id = gn.id " +
                        "inner join authors at on bk.author_id = at.id " +
                        "where bk.name = :bookName " +
                        "and at.name = :bookAuthor " +
                        "and gn.name = :bookGenre",
                parameterSource, new BookDaoJdbc.BookMapper());

        return books.stream().findFirst();
    }

    @Override
    public List<Book> getByAuthorName(String authorName) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("authorName", authorName);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        List<Book> books = namedParameterJdbcOperations.query(
                "select " +
                        "bk.id as book_id, " +
                        "bk.name as book_name, " +
                        "at.id as author_id, " +
                        "at.name as author_name, " +
                        "gn.id as genre_id, " +
                        "gn.name as genre_name " +
                        "from books bk " +
                        "inner join genres gn on bk.genre_id = gn.id " +
                        "inner join authors at on bk.author_id = at.id " +
                        "where at.name = :authorName ",
                parameterSource, new BookDaoJdbc.BookMapper());

        return books;
    }

    @Override
    public List<Book> getByGenreName(String genreName) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("genreName", genreName);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        List<Book> books = namedParameterJdbcOperations.query(
                "select " +
                        "bk.id as book_id, " +
                        "bk.name as book_name, " +
                        "at.id as author_id, " +
                        "at.name as author_name, " +
                        "gn.id as genre_id, " +
                        "gn.name as genre_name " +
                        "from books bk " +
                        "inner join genres gn on bk.genre_id = gn.id " +
                        "inner join authors at on bk.author_id = at.id " +
                        "where gn.name = :genreName ",
                parameterSource, new BookDaoJdbc.BookMapper());

        return books;
    }

    @Override
    public Optional<Book> getById(long id) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        List<Book> books3 = namedParameterJdbcOperations.query(
                "select " +
                    "bk.id as book_id, " +
                    "bk.name as book_name, " +
                    "at.id as author_id, " +
                    "at.name as author_name, " +
                    "gn.id as genre_id, " +
                    "gn.name as genre_name " +
                    "from books bk " +
                    "inner join genres gn on bk.genre_id = gn.id " +
                    "inner join authors at on bk.author_id = at.id where bk.id = :id",
                parameterSource, new BookDaoJdbc.BookMapper());

        return books3.stream().findFirst();
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select " +
                    "bk.id as book_id, " +
                    "bk.name as book_name, " +
                    "at.id as author_id, " +
                    "at.name as author_name, " +
                    "gn.id as genre_id, " +
                    "gn.name as genre_name " +
                    "from books bk " +
                    "inner join genres gn on bk.genre_id = gn.id " +
                    "inner join authors at on bk.author_id = at.id "
                , new BookDaoJdbc.BookMapper());
    }

    @Override
    public void deleteBookById(long bookId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("bookId", bookId);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "delete from books " +
                    "where id = :bookId"
                    ,parameterSource, keyHolder);
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcOperations.getJdbcOperations().update("delete from books");
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {

            return new Book(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    new Genre(resultSet.getLong("genre_id"), resultSet.getString("genre_name")),
                    new Author(resultSet.getLong("author_id"), resultSet.getString("author_name")));
        }
    }
}
