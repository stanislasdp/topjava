package ru.javawebinar.topjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.dto.MealDto;
import ru.javawebinar.topjava.mapper.MealToMealDtoMapper;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Autowired
    private MealToMealDtoMapper mapper;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public void add(MealDto dto, int userId) {
        Meal meal = mapper.mealDtoToMeal(dto);
        mealRepository.create(meal, userId);
    }

    @Override
    public List<MealDto> getWithinTime(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> meals = mealRepository.query(
                meal -> DateTimeUtil.isBetween(meal.getDate(), startDate.toLocalDate(), endDate.toLocalDate()) &&
                        DateTimeUtil.isBetween(meal.getTime(), startDate.toLocalTime(), endDate.toLocalTime()),
                userId);
        return meals.stream().map(meal -> mapper.mealToMealDto(meal)).collect(toList());
    }

    @Override
    public MealDto getById(Integer id, int userId) {
        Meal userMeal = mealRepository.readById(id, userId);
        checkNotFound(userMeal, String.format("meal with id %s", id));
        return mapper.mealToMealDto(userMeal);
    }

    @Override
    public void update(MealDto mealDto, int userId) {
        mealRepository.update(mapper.mealDtoToMeal(mealDto), userId);
    }

    @Override
    public void delete(Integer id, int userId) {
        mealRepository.delete(id, userId);
    }

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }
}
