package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.function.Predicate;

public interface MealRepository {

    void create(Meal meal);

    List<Meal> read();

    Meal readById(Long id);

    void update(Meal meal);

    void delete(Long id);

    List<Meal> query(Predicate<Meal> predicate);
}
