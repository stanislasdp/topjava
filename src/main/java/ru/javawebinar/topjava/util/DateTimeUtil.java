package ru.javawebinar.topjava.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss a", Locale.US);
    public static final DateTimeFormatter DATE_TIME_FORMATTER_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.US);
    public static final LocalDate MIN_LOCAL_DATE = LocalDate.of(1900, 1, 1);
    public static final LocalDate MAX_LOCAL_DATE = LocalDate.of(2100, 1, 1);

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
        return LocalDateTime.of(date == null ? MIN_LOCAL_DATE : date, time == null ? LocalTime.MIN : time);
    }

    public static LocalDateTime getLocalDateTimeMaxWhenEmpty(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date == null ? MAX_LOCAL_DATE : date, time == null ? LocalTime.MAX : time);
    }

}
