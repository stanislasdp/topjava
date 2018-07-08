package ru.javawebinar.topjava.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.javawebinar.topjava.model.AbstractBaseEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class MealWithExceed extends AbstractBaseEntity {


    @Getter
    private LocalDateTime dateTime;

    @Getter
    private String description;

    @Getter
    private int calories;

    @Getter
    private boolean exceed;

    public MealWithExceed() {
        super(null);
    }

    public MealWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        super(null);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }
}