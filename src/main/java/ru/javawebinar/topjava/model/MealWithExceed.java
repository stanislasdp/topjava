package ru.javawebinar.topjava.model;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class MealWithExceed {

    @Getter
    @Setter
    private Long id;

    @Getter
    private LocalDateTime dateTime;

    @Getter
    private String description;

    @Getter
    private int calories;

    @Getter
    private boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }
}