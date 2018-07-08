package ru.javawebinar.topjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.converter.MealToMealWithExceedConverter;
import ru.javawebinar.topjava.converter.MealWithExceedToMealConverter;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.specification.PredicateByDateRange;
import ru.javawebinar.topjava.repository.specification.PredicateByTimeRange;
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
        meal.setUserId(userId);
        mealRepository.create(meal);
    }

    @Override
    public List<MealWithExceed> getWithinTime(LocalDate startDate, LocalTime startTime,
                                              LocalDate endDate, LocalTime endTime, int userId) {
        List<Meal> meals = mealRepository.query(new PredicateByDateRange<Meal>(startDate, endDate)
                .and(new PredicateByTimeRange<>(startTime, endTime)));

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
        Meal userMeal = mealRepository.queryForSingle(meal -> meal.getId().equals(id));
        if (!userMeal.getUserId().equals(userId)) {
            throw new NotFoundException("meal does not belong to current user");
        }
        Objects.requireNonNull(userMeal);
        return mealToExceedMealConverter.convert(userMeal);
    }

    @Override
    public void update(MealWithExceed mealWithExceed, int userId) {
        Meal meal = mealRepository.readById(mealWithExceedToMealConverter.convert(mealWithExceed).getId());

        if (Objects.isNull(meal) || Objects.isNull(meal.getId())) {
            throw new NotFoundException("Meal does not exist");
        }

        if (!meal.getUserId().equals(userId)) {
            throw new NotFoundException("Meal does not exist");
        }
        mealRepository.update(meal);
    }

    @Override
    public void delete(Integer id, int userId) {
        Meal meal = mealRepository.readById(id);

        if (Objects.isNull(meal) || Objects.isNull(meal.getId())) {
            throw new NotFoundException("Meal does not exist");
        }

        if (!meal.getUserId().equals(userId)) {
            throw new NotFoundException("meal doesnot belong to the user");
        }
        mealRepository.delete(id);
    }
}
