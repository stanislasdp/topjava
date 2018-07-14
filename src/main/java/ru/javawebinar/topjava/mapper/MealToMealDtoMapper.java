package ru.javawebinar.topjava.mapper;


import org.mapstruct.Mapper;
import ru.javawebinar.topjava.dto.MealDto;
import ru.javawebinar.topjava.model.Meal;

@Mapper
public interface MealToMealDtoMapper {

    MealDto mealToMealDto(Meal meal);

    Meal mealDtoToMeal(MealDto mealDto);
}
