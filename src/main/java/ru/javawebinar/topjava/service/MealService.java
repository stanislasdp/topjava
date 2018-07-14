package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dto.MealDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {

    void add(MealDto meal, int userId);

    List<MealDto> getWithinTime(LocalDateTime startDate, LocalDateTime endDate, int userId);

    MealDto getById(Integer id, int userId);

    void update(MealDto meal, int userId);

    void delete(Integer id, int userId);

}
