package ru.javawebinar.topjava.converter;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

@Component
public class MealToMealWithExceedConverter implements Converter<Meal, MealWithExceed> {

    private static final Boolean DEFAULT_EXCEED = false;

    @Override
    public MealWithExceed convert(Meal source) {
        MealWithExceed meal = new MealWithExceed(source.getDateTime(), source.getDescription(), source.getCalories(), DEFAULT_EXCEED);
        meal.setId(source.getId());
        return meal;
    }

    public MealWithExceed convert(Meal source, boolean isExceeded) {
        MealWithExceed mealWithExceed = new MealWithExceed(source.getDateTime(), source.getDescription(), source.getCalories(), isExceeded);
        mealWithExceed.setId(source.getId());
        return mealWithExceed;
    }
}
