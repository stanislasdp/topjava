package ru.javawebinar.topjava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonMap;

@Component
public class UserRepositoryJdbcImpl implements UserRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public UserRepositoryJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public User save(User user) {
        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("caloriesPerDay", user.getCaloriesPerDay())
                .addValue("id", user.getId());
        if (user.isNew()) {
            Number number = simpleJdbcInsert.withTableName("users").executeAndReturnKey(mapSqlParameterSource);
            user.setId(number.intValue());
        } else {
            jdbcTemplate.update("UPDATE users SET name=:name, email=:email, password=:password," +
                            "registered=:registered, enabled=:enabled, calories_per_date=:caloriesPerDay " +
                            "where id=:id", mapSqlParameterSource);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM USERS where id=:id", singletonMap("id", id)) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users JOIN user_roles ON id = user_id WHERE id =:id",
                singletonMap("id", id), userRowMapper());
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM Users JOIN User_Roles ON id = user_id", userRowMapper());
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.getJdbcTemplate().queryForObject("SELECT FROM USERS WHERE email =?",
                userRowMapper(), email);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"),
                rs.getString("email"), rs.getString("password"),
                rs.getInt("calories_per_day"), rs.getBoolean("enabled"),
                Collections.singleton(roleMapper(rs, rowNum)));
    }

    private Role roleMapper(ResultSet resultSet, int rowNum) throws SQLException {
        return Role.valueOf(resultSet.getString("role"));
    }

}
