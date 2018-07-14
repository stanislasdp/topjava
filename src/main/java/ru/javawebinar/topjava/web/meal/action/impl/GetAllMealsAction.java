package ru.javawebinar.topjava.web.meal.action.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.meal.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getLocalDateIfPresent;
import static ru.javawebinar.topjava.util.DateTimeUtil.getLocalTimeIfPresent;


public class GetAllMealsAction implements Action {
    private static final String URL_PATTERN = "/getAllMeals";

    private MealRestController controller;

    @Autowired
    public GetAllMealsAction(MealRestController controller) {
        this.controller = controller;
    }

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        LocalDate startDate = getLocalDateIfPresent(request.getParameter("startDate"));
        LocalDate endDate = getLocalDateIfPresent(request.getParameter("endDate"));

        LocalTime startTime = getLocalTimeIfPresent(request.getParameter("startTime"));
        LocalTime endTime = getLocalTimeIfPresent(request.getParameter("endTime"));

        List<MealWithExceed> meals = controller.getExceeded(startDate, endDate, startTime, endTime);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/listMeal.jsp").forward(request, response);
    }
}
