package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dto.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    void add(MealWithExceed mealWithExceed, int userId);

    List<MealWithExceed> getWithinTime(LocalDate startDate, LocalTime startTime,
                                       LocalDate endDate, LocalTime endTime,
                                       int userId);

    MealWithExceed getById(Integer id, int userId);

    void update(MealWithExceed mealWithExceed, int userId);

    void delete(Integer id, int userId);

}
