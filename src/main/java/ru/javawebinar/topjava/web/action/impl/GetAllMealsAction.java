package ru.javawebinar.topjava.web.action.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class GetAllMealsAction implements Action {
    private static final String URL_PATTERN = "/getAllMeals";

    private MealService mealService;

    @Autowired
    public GetAllMealsAction(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public boolean doGet(HttpServletRequest request, HttpServletResponse response) {
        Integer calories = request.getAttribute("calories") == null ? 0 : (int) request.getAttribute("calories");
        List<MealWithExceed> meals = mealService.getAll(calories);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/listMeal.jsp").forward(request, response);
        return false;
    }

    @Override
    public boolean doPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Integer calories = Integer.parseInt(parameterMap.get("calories")[0]);
        request.setAttribute("calories", calories);
        doGet(request, response);
        return false;
    }
}
