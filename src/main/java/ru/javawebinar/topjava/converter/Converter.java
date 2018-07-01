package ru.javawebinar.topjava.converter;

public interface Converter<T, R> {

    R convert(T source);
}
