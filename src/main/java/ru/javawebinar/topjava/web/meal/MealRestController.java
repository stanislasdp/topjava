package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.converter.MealDtoToMealWithExceedConverter;
import ru.javawebinar.topjava.dto.MealDto;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.SecurityUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.MAX_LOCAL_DATE;
import static ru.javawebinar.topjava.util.DateTimeUtil.MIN_LOCAL_DATE;
import static ru.javawebinar.topjava.util.GenericUtils.getOrDefault;
import static ru.javawebinar.topjava.util.SecurityUtil.authUserId;

@Component
public class MealRestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MealService service;

    private MealDtoToMealWithExceedConverter mealDtoToMealWithExceedConverter;

    public MealRestController() {
    }

    @Autowired
    public MealRestController(MealService mealService, MealDtoToMealWithExceedConverter converter) {
        this.service = mealService;
        this.mealDtoToMealWithExceedConverter = converter;
    }

    public List<MealWithExceed> getExceeded(LocalDate startDate, LocalDate endDate,
                                            LocalTime startTime, LocalTime endTime) {
        log.info("getAll");
        List<MealDto> meals = service.getWithinTime(
            LocalDateTime.of(getOrDefault(startDate, MIN_LOCAL_DATE), getOrDefault(startTime, LocalTime.MIN)),
            LocalDateTime.of(getOrDefault(endDate, MAX_LOCAL_DATE), getOrDefault(endTime, LocalTime.MIN)),
            authUserId());
        return mealDtoToMealWithExceedConverter.convert(meals, SecurityUtil.authUserCaloriesPerDay());
    }

    public MealDto getById(int id) {
        log.info("getExceeded {}", id);
        return service.getById(id, authUserId());
    }

    public void add(MealDto meal) {
        service.add(meal, authUserId());
    }

    public void update(int id, MealDto meal) {
        log.info("update {} with id {}", meal, id);
        ValidationUtil.assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete with id {}", id);
        service.delete(id, authUserId());
    }
}
