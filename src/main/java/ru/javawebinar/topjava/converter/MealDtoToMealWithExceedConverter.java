package ru.javawebinar.topjava.converter;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.dto.MealDto;
import ru.javawebinar.topjava.dto.MealWithExceed;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Component
public class MealDtoToMealWithExceedConverter {


    public List<MealWithExceed> convert(List<MealDto> meals, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(groupingBy(MealDto::getDate, summingInt(MealDto::getCalories)));

        return meals.stream()
                .map(meal ->  convert(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .sorted(comparing(MealWithExceed::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    private MealWithExceed convert(MealDto dto, boolean isExceeded) {
        return new MealWithExceed(dto.getId(), dto.getDateTime(), dto.getDescription(), dto.getCalories(), isExceeded);
    }

}
