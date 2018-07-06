package ru.javawebinar.topjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.converter.MealToMealWithExceedConverter;
import ru.javawebinar.topjava.converter.MealWithExceedToMealConverter;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.specification.MealPredicateByDateRange;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    private MealToMealWithExceedConverter mealToExceedMealConverter;

    private MealWithExceedToMealConverter mealWithExceedToMealConverter;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository,
                           MealToMealWithExceedConverter mealToExceedMealConverter,
                           MealWithExceedToMealConverter mealWithExceedToMealConverter) {
        this.mealRepository = mealRepository;
        this.mealToExceedMealConverter = mealToExceedMealConverter;
        this.mealWithExceedToMealConverter = mealWithExceedToMealConverter;
    }

    @Override
    public void add(MealWithExceed mealWithExceed) {
        Meal meal = mealWithExceedToMealConverter.convert(mealWithExceed);
        mealRepository.create(meal);
    }

    @Override
    public List<MealWithExceed> getWithinTime(int calories, LocalTime startTime, LocalTime endTime) {
        return mealRepository.query(new MealPredicateByDateRange(startTime, endTime))
            .stream()
            .map(meal -> mealToExceedMealConverter.convert(meal))
            .collect(Collectors.toList());
    }

    @Override
    public List<MealWithExceed> getAll(int caloriesNorm) {
        List<Meal> meals = mealRepository.read();

        Map<LocalDate, Integer> caloriesSumByDate = meals
                .stream()
                .collect(groupingBy(Meal::getDate, summingInt(Meal::getCalories)));

        return meals.stream()
                .map(meal -> mealToExceedMealConverter.convert(meal, caloriesSumByDate.get(meal.getDate()) > caloriesNorm))
                .collect(Collectors.toList());
    }

    @Override
    public MealWithExceed getById(Long id) {
        Meal meal = mealRepository.readById(id);
        return mealToExceedMealConverter.convert(meal);
    }

    @Override
    public void update(MealWithExceed mealWithExceed) {
        mealRepository.update(mealWithExceedToMealConverter.convert(mealWithExceed));
    }

    @Override
    public void delete(Long id) {
        mealRepository.delete(id);
    }
}
