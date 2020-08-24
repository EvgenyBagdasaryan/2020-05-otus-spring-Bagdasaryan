package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao  {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update("insert into genres (id, `name`) values (?, ?)", genre.getId(), genre.getName());
    }

    @Override
    public long insertByName(String genreName) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("genreName", genreName);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "insert into genres (name) values (:genreName)", parameterSource, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<Genre> getById(long id) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        return namedParameterJdbcOperations.query(
                "select * from genres where id = :id", parameterSource, new GenreDaoJdbc.GenreMapper()).stream().findFirst();
    }

    @Override
    public Genre getById2(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from genres where id = :id", params, new GenreDaoJdbc.GenreMapper()
        );
    }

    @Override
    public Optional<Genre> getByName(String genreName) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("genreName", genreName);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        return namedParameterJdbcOperations.query(
                "select * from genres where name = :genreName", parameterSource, new GenreDaoJdbc.GenreMapper()).stream().findFirst();
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public void updateGenre(String oldNameGenre, String newNameGenre) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("oldNameGenre", oldNameGenre)
                .addValue("newNameGenre", newNameGenre);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update(
                "update genres set name = :newNameGenre where name = :oldNameGenre", parameterSource, keyHolder);
    }

    @Override
    public void deleteByName(String genreName) {
        Map<String, Object> params = Collections.singletonMap("genreName", genreName);
        namedParameterJdbcOperations.update(
                "delete from genres where name = :genreName", params
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from genres where id = :id", params
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
