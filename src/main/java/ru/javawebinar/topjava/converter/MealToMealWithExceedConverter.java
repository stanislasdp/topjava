package ru.javawebinar.topjava.converter;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.model.Meal;

@Component
public class MealToMealWithExceedConverter implements Converter<Meal, MealWithExceed> {

    private static final Boolean DEFAULT_EXCEED = false;

    @Override
    public MealWithExceed convert(Meal source) {
        return new MealWithExceed( source.getId(), source.getDateTime(), source.getDescription(), source.getCalories(), DEFAULT_EXCEED);
    }

    public MealWithExceed convert(Meal source, boolean isExceeded) {
        return new MealWithExceed(source.getId(),
                source.getDateTime(), source.getDescription(), source.getCalories(), isExceeded);
    }
}
