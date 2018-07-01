package ru.javawebinar.topjava.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    boolean canProcess(HttpServletRequest request);

    boolean doGet(HttpServletRequest request, HttpServletResponse response);

    boolean doPost(HttpServletRequest request, HttpServletResponse response);

    default String getActionString(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
