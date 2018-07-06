package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.function.Predicate;

public interface MealRepository {

    void create(Meal meal);

    List<Meal> read();

    Meal readById(Integer id);

    void update(Meal meal);

    void delete(Integer id);

    List<Meal> query(Predicate<Meal> predicate);

    Meal queryForSingle(Predicate<Meal> predicate);
}
