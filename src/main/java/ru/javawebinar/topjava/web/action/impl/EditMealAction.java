package ru.javawebinar.topjava.web.action.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class EditMealAction implements Action {

    private static final String URL_PATTERN = "/edit";

    @Autowired
    private MealService mealService;

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public boolean doGet(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("mealId"));
        MealWithExceed meal = mealService.getById(id);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
        return true;
    }

    @Override
    @SneakyThrows
    public boolean doPost(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        Integer calories = Integer.parseInt(request.getParameter("calories"));
        MealWithExceed meal = new MealWithExceed(dateTime, description, calories, false);
        meal.setId(id);
        mealService.update(meal);
        response.sendRedirect(request.getContextPath() + "/meals/getAllMeals");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/meals/getAllMeals");
        requestDispatcher.forward(request, response);
        response.setStatus(200);

        return false;
    }
}