package ru.javawebinar.topjava.repository;

import com.google.common.collect.ImmutableMap;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.DATE_TIME_FORMATTER;
import static ru.javawebinar.topjava.util.DateTimeUtil.DATE_TIME_FORMATTER_DB;

public class MealRepositoryJdbcImpl implements MealRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    public MealRepositoryJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public Meal create(Meal meal, int userId) {
        SqlParameterSource parameterSource = getSqlParameterSource(meal, userId);
        Number key = simpleJdbcInsert.withTableName("meals").executeAndReturnKey(parameterSource);
        meal.setId(key.intValue());
        return meal;
    }


    @Override
    public Meal readById(int id, int userId) {
        return DataAccessUtils.singleResult(jdbcTemplate.query("SELECT FROM meals where user_id=:userId",
            Collections.singletonMap("userId", userId), mealRowMapper()));
    }

    @Override
    public Meal update(Meal meal, int userId) {
        if (meal.isNew()) {
            throw new RuntimeException("meal is new");
        }
        jdbcTemplate.update("UPDATE meals SET date_time=:dateTime, description=:description, calories=:calories" +
            "where id=:id AND user_id=:userId", getSqlParameterSource(meal, userId));
        return meal;
    }

    @Override
    public boolean delete(Integer id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals where id=:id and user_id =:userId",
            ImmutableMap.of("id", id, "userId", userId)) != 0;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE (date_time BETWEEN :startDate and :endDate)",
            ImmutableMap.of("startDate", startDate.format(DATE_TIME_FORMATTER_DB), "endDate",
                endDate.format(DATE_TIME_FORMATTER_DB)), mealRowMapper());
    }

    private RowMapper<Meal> mealRowMapper() {
        return (rs, rowNum) -> new Meal(rs.getInt("id"), rs.getTimestamp("date_time").toLocalDateTime(),
            rs.getString("description"), rs.getInt("calories"));
    }

    private SqlParameterSource getSqlParameterSource(Meal meal, int userId) {
        return new MapSqlParameterSource()
            .addValue("id", meal.getId())
            .addValue("dateTime", meal.getDateTime().format(DATE_TIME_FORMATTER))
            .addValue("description", meal.getDescription())
            .addValue("calories", meal.getCalories())
            .addValue("userId", userId);
    }
}
