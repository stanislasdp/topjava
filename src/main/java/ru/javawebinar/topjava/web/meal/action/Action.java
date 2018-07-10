package ru.javawebinar.topjava.web.meal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    boolean canProcess(HttpServletRequest request);


    default void doPost(HttpServletRequest request, HttpServletResponse response){
    }

    default void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    default String getActionString(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
