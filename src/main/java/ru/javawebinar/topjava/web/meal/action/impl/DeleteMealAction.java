package ru.javawebinar.topjava.web.meal.action.impl;

import lombok.SneakyThrows;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.meal.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMealAction implements Action {

    private MealRestController controller;

    public DeleteMealAction(MealRestController controller) {
        this.controller = controller;
    }

    private static final String URL_PATTERN = "/delete";

    @Override
    public boolean canProcess(HttpServletRequest request) {
        return getActionString(request).endsWith(URL_PATTERN);
    }

    @Override
    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        controller.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("meals/getAllMeals");
    }
}
