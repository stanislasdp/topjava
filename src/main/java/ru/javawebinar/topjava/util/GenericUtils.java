package ru.javawebinar.topjava.util;

public class GenericUtils {

    public static <T> T getOrDefault(T object, T defaultValue) {
        return object != null ? object : defaultValue;
    }

    public static <T extends Comparable<? super T>> boolean isBetween(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }
}
