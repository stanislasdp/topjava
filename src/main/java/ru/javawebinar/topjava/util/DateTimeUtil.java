package ru.javawebinar.topjava.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss a", Locale.US);
    public static final DateTimeFormatter DATE_TIME_FORMATTER_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.US);
    public static final LocalDate MIN_LOCAL_DATE = LocalDate.of(1900, 1, 1);
    public static final LocalDate MAX_LOCAL_DATE = LocalDate.of(2100, 1, 1);

    public static LocalDate getLocalDateIfPresent(String localDate) {
        return StringUtils.isNotBlank(localDate) ? LocalDate.parse(localDate) : null;
    }

    public static LocalTime getLocalTimeIfPresent(String localTime) {
        return StringUtils.isNotBlank(localTime) ? LocalTime.parse(localTime) : null;
    }



}
