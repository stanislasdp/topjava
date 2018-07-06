package ru.javawebinar.topjava.converter;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.dto.MealWithExceed;

@Component
public class MealWithExceedToMealConverter implements Converter<MealWithExceed, Meal> {
    @Override
    public Meal convert(MealWithExceed source) {
        return new Meal(source.getId(), source.getDateTime(), source.getDescription(), source.getCalories());
    }
}
