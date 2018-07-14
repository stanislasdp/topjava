package ru.javawebinar.topjava.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.javawebinar.topjava.model.AbstractBaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
public class MealDto extends AbstractBaseEntity {


    @Getter
    @Setter
    private LocalDateTime dateTime;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int calories;

    public MealDto() {
    }

    public MealDto(Integer id, LocalDateTime dateTime, String description, int calories) {
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
