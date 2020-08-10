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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao  {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public void insertByAuthor(Author author) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("name", author.getName());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into authors (id, name) values (:id, :name)", parameterSource, keyHolder);
    }

    @Override
    public long insertByFullName(String fullName) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fullName", fullName);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into authors (name) values (:fullName)", parameterSource, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<Author> getById(long id) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        return namedParameterJdbcOperations.query(
                "select * from authors where id = :id", parameterSource, new AuthorDaoJdbc.AuthorMapper()).stream().findFirst();
    }

    @Override
    public Optional<Author> getByFullName(String fullName) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fullName", fullName);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        return namedParameterJdbcOperations.query(
                "select * from authors where name = :fullName", parameterSource, new AuthorDaoJdbc.AuthorMapper()).stream().findFirst();
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                "select * from authors", new AuthorDaoJdbc.AuthorMapper());
    }

    @Override
    public void updateAuthor(String fullNameOld, String fullNameNew) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fullNameOld", fullNameOld)
                .addValue("fullNameNew", fullNameNew);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "update authors set name = :fullNameNew where name = :fullNameOld", parameterSource, keyHolder);
    }

    @Override
    public void deleteByFullName(String fullName) {
        Map<String, Object> params = Collections.singletonMap("fullName", fullName);
        namedParameterJdbcOperations.update(
                "delete from authors where name = :fullName", params
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from authors where id = :id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}