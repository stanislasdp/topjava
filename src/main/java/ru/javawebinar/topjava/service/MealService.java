package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalTime;
import java.util.List;

public interface MealService {

    void add(MealWithExceed mealWithExceed);

    List<MealWithExceed> getWithinTime(int caloriesNorm, LocalTime start, LocalTime end);

    List<MealWithExceed> getAll(int caloriesNorm);

    MealWithExceed getById(Long id);

    void update(MealWithExceed mealWithExceed);

    void delete(Long id);


}
