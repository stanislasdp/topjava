package ru.javawebinar.topjava.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EqualsAndHashCode
@ToString
public class Meal extends AbstractBaseEntity {

    @Getter
    @Setter
    private LocalDateTime dateTime;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int calories;

    public Meal(Integer id) {
        super(id);
    }

    public Meal() {
        super();
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
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
