package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.function.Predicate;

public interface MealRepository {

    Meal create(Meal meal, int userId);

    List<Meal> read(int userId);

    Meal readById(int id, int userId);

    Meal update(Meal meal, int userId);

    boolean delete(Integer id, int userId);

    List<Meal> query(Predicate<Meal> predicate, int userId);

    Meal queryForSingle(Predicate<Meal> predicate, int userId);
}
