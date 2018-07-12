package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dto.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {

    void add(MealWithExceed mealWithExceed, int userId);

    List<MealWithExceed> getWithinTime(LocalDateTime startDate,
                                       LocalDateTime endDate,
                                       int userId, int caloriesNorm);

    MealWithExceed getById(Integer id, int userId);

    void update(MealWithExceed mealWithExceed, int userId);

    void delete(Integer id, int userId);

}
