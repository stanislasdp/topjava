package ru.javawebinar.topjava.repository.specification;

import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalTime;
import java.util.Objects;
import java.util.function.Predicate;

public class PredicateByTimeRange<T> implements Predicate<T> {

    private LocalTime start;
    private LocalTime end;

    public PredicateByTimeRange(LocalTime start, LocalTime end) {
        this.start = Objects.nonNull(start) ? start : LocalTime.MIN;
        this.end = Objects.nonNull(end) ? end : LocalTime.MAX;
    }

    @Override
    public boolean test(T t) {
        return DateTimeUtil.isBetween(LocalTime.now(), start, end);
    }
}
