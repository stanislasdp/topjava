package ru.javawebinar.topjava.repository.specification;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import java.time.LocalTime;
import java.util.function.Predicate;

public class MealPredicateByDateRange implements Predicate<Meal> {

    private LocalTime timeFrom;
    private LocalTime timeTo;

    public MealPredicateByDateRange(LocalTime timeFrom, LocalTime timeTwo) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTwo;
    }

    @Override
    public boolean test(Meal meal) {
        return DateTimeUtil.isBetween(LocalTime.now(), timeFrom, timeTo);
    }
}
