package ru.javawebinar.topjava.web.meal.action.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DeleteMealAction implements Action {

    @Autowired
    private MealService mealService;

    private static final String URL_PATTERN = "/delete";

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public boolean doGet(HttpServletRequest request, HttpServletResponse response) {
        mealService.delete(Long.parseLong(request.getParameter("id")));
        response.sendRedirect("meals/getAllMeals");
        return false;
    }
}
