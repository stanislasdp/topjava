package ru.javawebinar.topjava.web.meal.action.impl;

import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.meal.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GetAllMealsAction implements Action {
    private static final String URL_PATTERN = "/getAllMeals";

    private MealRestController controller;

    public GetAllMealsAction(MealRestController controller) {
        this.controller = controller;
    }

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public boolean doGet(HttpServletRequest request, HttpServletResponse response) {
        String startDateString = request.getParameter("startDate");
        LocalDate startDate = StringUtils.isNotBlank(startDateString) ? LocalDate.parse(startDateString) : null;

        String endDateString = request.getParameter("endDate");
        LocalDate endDate = StringUtils.isNotBlank(endDateString) ? LocalDate.parse(endDateString) : null;

        String startTimeString = request.getParameter("startTime");
        LocalTime startTime = StringUtils.isNotBlank(startTimeString) ? LocalTime.parse(startTimeString) : null;

        String endTimeString = request.getParameter("endTime");
        LocalTime endTime = StringUtils.isNotBlank(endTimeString) ? LocalTime.parse(endTimeString) : null;

        List<MealWithExceed> meals = ObjectUtils.anyNotNull(startDate, startTime, endDate, endTime)
                ? controller.getWithinDatesTimes(startDate, endDate, startTime, endTime)
                : controller.getAll();

        if (StringUtils.isNotBlank(startDateString)) {
            request.setAttribute("startDate", startDateString);
        }
        if (StringUtils.isNotBlank(endDateString)) {
            request.setAttribute("endDate", endDateString);
        }

        if (StringUtils.isNotBlank(startTimeString)) {
            request.setAttribute("startTime", startTimeString);
        }

        if (StringUtils.isNotBlank(endTimeString)) {
            request.setAttribute("endTime", endTimeString);
        }
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/listMeal.jsp").forward(request, response);
        return false;
    }
}
