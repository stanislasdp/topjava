package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal create(Meal meal, int userId);


    Meal readById(int id, int userId);

    Meal update(Meal meal, int userId);

    boolean delete(Integer id, int userId);

    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
