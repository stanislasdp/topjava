package ru.javawebinar.topjava.web.action.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class AddNewMealAction implements Action {

    private static final String URL_PATTERN ="/add";

    @Autowired
    private MealService mealService;

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public boolean doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("meal", new MealWithExceed());
        request.getRequestDispatcher("/meal.jsp").forward(request, response);
        return true;
    }

    @Override
    @SneakyThrows
    public boolean doPost(HttpServletRequest request, HttpServletResponse response) {
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        Integer calories = Integer.parseInt(request.getParameter("calories"));
        MealWithExceed meal = new MealWithExceed(dateTime, description, calories, false);
        mealService.add(meal);
        response.sendRedirect("meals/getAllMeals");
        return true;
    }
}
