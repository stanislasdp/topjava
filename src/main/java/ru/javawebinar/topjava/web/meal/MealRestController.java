package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MealService service;

    @Autowired
    public MealRestController(MealService mealService) {
        this.service = mealService;
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return service.getWithinTime(LocalDate.MIN, LocalTime.MIN,
                LocalDate.MAX, LocalTime.MAX,
                authUserId());
    }

    public MealWithExceed getById(int id) {
        log.info("get {}", id);
        return service.getById(id, authUserId());
    }

    public List<MealWithExceed> getWithinDatesTimes(LocalDate startDate, LocalDate endDate,
                                                    LocalTime startTime, LocalTime endTime) {
        log.info("get within start date:{}, start time:{}, end date:{}, end time: {}",
                startDate, endDate, startTime, endTime);
        return service.getWithinTime(startDate, startTime, endDate, endTime, authUserId());
    }

    public void add(MealWithExceed mealWithExceed) {
        service.add(mealWithExceed, authUserId());
    }

    public void update(int id, MealWithExceed meal) {
        log.info("update {} with id {}", meal, id);
        ValidationUtil.assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete with id {}", id);
        service.delete(id, authUserId());
    }


}
