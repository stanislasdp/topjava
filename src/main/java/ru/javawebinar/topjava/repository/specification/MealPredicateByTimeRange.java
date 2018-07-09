package ru.javawebinar.topjava.repository.specification;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalTime;
import java.util.Objects;
import java.util.function.Predicate;

public class MealPredicateByTimeRange<T extends Meal> implements Predicate<T> {

    private LocalTime start;
    private LocalTime end;

    public MealPredicateByTimeRange(LocalTime start, LocalTime end) {
        this.start = Objects.nonNull(start) ? start : LocalTime.MIN;
        this.end = Objects.nonNull(end) ? end : LocalTime.MAX;
    }

    @Override
    public boolean test(T meal) {
        return DateTimeUtil.isBetween(meal.getTime(), start, end);
    }
}
