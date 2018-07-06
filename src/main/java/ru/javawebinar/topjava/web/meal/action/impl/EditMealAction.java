package ru.javawebinar.topjava.web.meal.action.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class EditMealAction implements Action {

    private static final String URL_PATTERN = "update";

    @Autowired
    private MealService mealService;

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public boolean doGet(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        MealWithExceed meal = mealService.getById(id);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/meal.jsp").forward(request, response);
        return true;
    }

    @Override
    @SneakyThrows
    public boolean doPost(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        Integer calories = Integer.parseInt(request.getParameter("calories"));
        MealWithExceed meal = new MealWithExceed(dateTime, description, calories, false);
        meal.setId(id);
        mealService.update(meal);
        response.sendRedirect("meals/getAllMeals");
        return false;
    }
}