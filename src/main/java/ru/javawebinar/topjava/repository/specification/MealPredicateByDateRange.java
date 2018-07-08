package ru.javawebinar.topjava.repository.specification;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

public class MealPredicateByDateRange<T extends Meal> implements Predicate<T> {

    private LocalDate timeFrom;
    private LocalDate timeTo;

    public MealPredicateByDateRange(LocalDate timeFrom, LocalDate timeTo) {
        this.timeFrom = Objects.nonNull(timeFrom) ? timeFrom : LocalDate.MIN;
        this.timeTo = Objects.nonNull(timeTo) ? timeTo : LocalDate.MAX;
    }

    @Override
    public boolean test(T meal) {
        return DateTimeUtil.isBetween(meal.getDate(), timeFrom, timeTo);
    }
}
