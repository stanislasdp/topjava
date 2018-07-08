package ru.javawebinar.topjava.repository.specification;

import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

public class PredicateByDateRange<T> implements Predicate<T> {

    private LocalDate timeFrom;
    private LocalDate timeTo;

    public PredicateByDateRange(LocalDate timeFrom, LocalDate timeTo) {
        this.timeFrom = Objects.nonNull(timeFrom) ? timeFrom : LocalDate.MIN;
        this.timeTo = Objects.nonNull(timeTo) ? timeTo : LocalDate.MAX;
    }

    @Override
    public boolean test(T object) {
        return DateTimeUtil.isBetween(LocalDate.now(), timeFrom, timeTo);
    }
}
