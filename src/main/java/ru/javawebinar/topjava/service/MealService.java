package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.model.User;

import java.time.LocalTime;
import java.util.List;

public interface MealService {

    void add(MealWithExceed mealWithExceed);

    List<MealWithExceed> getWithinTime(int caloriesNorm, LocalTime start, LocalTime end);

    List<MealWithExceed> getAll(User user);

    MealWithExceed getById(User user, Integer id);

    void update(MealWithExceed mealWithExceed);

    void delete(Integer id);

}
