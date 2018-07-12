package ru.javawebinar.topjava.web.meal.action.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.meal.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class EditMealAction implements Action {

    private static final String URL_PATTERN = "update";

    private MealRestController controller;

    @Autowired
    public EditMealAction(MealRestController controller) {
        this.controller = controller;
    }

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        Integer id = Integer.parseInt(request.getParameter("id"));
        MealWithExceed meal = controller.getById(id);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/meal.jsp").forward(request, response);
    }

    @Override
    @SneakyThrows
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Integer mealId = Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        Integer calories = Integer.parseInt(request.getParameter("calories"));
        MealWithExceed meal = new MealWithExceed(mealId, dateTime, description, calories, false);
        meal.setId(mealId);
        controller.update(mealId, meal);
        response.sendRedirect("meals/getAllMeals");
    }
}