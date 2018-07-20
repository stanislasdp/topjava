package ru.javawebinar.topjava.repository;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.DATE_TIME_FORMATTER_DB;

@Component
public class MealRepositoryJdbcImpl implements MealRepository {

    private static final String MEALS_TABLE = "meals";

    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public MealRepositoryJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public Meal create(Meal meal, int userId) {
        SqlParameterSource parameterSource = getSqlParameterSource(meal, userId);
        Number key = simpleJdbcInsert.withTableName(MEALS_TABLE)
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(parameterSource);
        meal.setId(key.intValue());
        return meal;
    }

    @Override
    public Meal readById(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals where id=:id and user_id=:userId",
            ImmutableMap.of("userId", userId, "id", id), mealRowMapper());
        return CollectionUtils.isEmpty(meals) ? null : DataAccessUtils.singleResult(meals);
    }

    @Override
    public Meal update(Meal meal, int userId) {
        if (meal.isNew()) {
            throw new RuntimeException("meal is new");
        }
        jdbcTemplate.update("UPDATE meals SET date_time=:date_time, description=:description, calories=:calories" +
            " where id=:id AND user_id=:user_id", getSqlParameterSource(meal, userId));
        return meal;
    }

    @Override
    public boolean delete(Integer id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals where id=:id and user_id =:user_id",
            ImmutableMap.of("id", id, "user_id", userId)) != 0;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("startDate", startDate.format(DATE_TIME_FORMATTER_DB))
                .addValue("endDate", endDate.format(DATE_TIME_FORMATTER_DB))
                .addValue("userId", userId);
        return jdbcTemplate.query("SELECT * FROM meals WHERE (date_time BETWEEN :startDate and :endDate) " +
                        "and user_id=:userId ORDER BY date_time DESC", parameterSource, mealRowMapper());
    }

    private RowMapper<Meal> mealRowMapper() {
        return (rs, rowNum) -> new Meal(rs.getInt("id"), rs.getTimestamp("date_time").toLocalDateTime(),
            rs.getString("description"), rs.getInt("calories"));
    }

    private SqlParameterSource getSqlParameterSource(Meal meal, int userId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("date_time", meal.getDateTime().format(DATE_TIME_FORMATTER_DB))
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories())
                .addValue("user_id", userId);

        if (Objects.nonNull(meal.getId())) {
            parameterSource.addValue("id", meal.getId());
        }
        return parameterSource;
    }
}
