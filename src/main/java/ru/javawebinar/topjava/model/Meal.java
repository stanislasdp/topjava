package ru.javawebinar.topjava.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EqualsAndHashCode
@ToString
public class Meal {

    @Getter
    private Long id;

    @Getter
    private LocalDateTime dateTime;

    @Getter
    private String description;

    @Getter
    private int calories;

    public Meal() {
    }

    public Meal(Long id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }


    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
