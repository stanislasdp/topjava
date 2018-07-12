package ru.javawebinar.topjava.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss a",
            Locale.US);

    public static <T extends Comparable<? super T>> boolean isBetween(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate getLocalDateIfPresent(String localDate) {
        return StringUtils.isNotBlank(localDate) ? LocalDate.parse(localDate) : null;
    }

    public static LocalTime getLocalTimeIfPresent(String localTime) {
        return StringUtils.isNotBlank(localTime) ? LocalTime.parse(localTime) : null;
    }

    public static LocalDateTime getLocalDateTimeMinWhenEmpty(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date == null ? LocalDate.MIN : date, time == null ? LocalTime.MIN : time);
    }

    public static LocalDateTime getLocalDateTimeMaxWhenEmpty(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date == null ? LocalDate.MAX : date, time == null ? LocalTime.MAX : time);
    }

}
