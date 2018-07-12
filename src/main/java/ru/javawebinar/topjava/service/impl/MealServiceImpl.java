package ru.javawebinar.topjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.converter.MealToMealWithExceedConverter;
import ru.javawebinar.topjava.converter.MealWithExceedToMealConverter;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.specification.MealPredicateByDateRange;
import ru.javawebinar.topjava.repository.specification.MealPredicateByTimeRange;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.SecurityUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;

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
    public void add(MealWithExceed mealWithExceed, int userId) {
        Meal meal = mealWithExceedToMealConverter.convert(mealWithExceed);
        mealRepository.create(meal, userId);
    }

    @Override
    public List<MealWithExceed> getWithinTime(LocalDate startDate, LocalTime startTime,
                                              LocalDate endDate, LocalTime endTime, int userId) {
        List<Meal> meals = mealRepository.query(
            new MealPredicateByDateRange<>(startDate, endDate)
                .and(new MealPredicateByTimeRange<>(startTime, endTime)), userId);

        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
            .collect(groupingBy(Meal::getDate, summingInt(Meal::getCalories)));

        return meals.stream()
            .map(meal -> mealToExceedMealConverter.convert(meal, caloriesSumByDate
                .get(meal.getDate()) > SecurityUtil.authUserCaloriesPerDay()))
            .sorted(comparing(MealWithExceed::getDateTime).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public MealWithExceed getById(Integer id, int userId) {
        Meal userMeal = mealRepository.readById(id, userId);
        checkNotFound(userMeal, String.format("meal with id %s", id));
        return mealToExceedMealConverter.convert(userMeal);
    }

    @Override
    public void update(MealWithExceed mealWithExceed, int userId) {
        mealRepository.update(mealWithExceedToMealConverter.convert(mealWithExceed), userId);
    }

    @Override
    public void delete(Integer id, int userId) {
        mealRepository.delete(id, userId);
    }
}
