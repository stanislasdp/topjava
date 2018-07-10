package ru.javawebinar.topjava.web.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.javawebinar.topjava.web.meal.action.Action;
import ru.javawebinar.topjava.web.meal.action.ActionsResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MealServlet extends HttpServlet {

    @Autowired
    private MealRestController controller;

    private List<Action> actions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
        actions = new ActionsResolver(controller).getActions();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        getAction(req).doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        getAction(req).doPost(req, resp);
    }

    private Action getAction(HttpServletRequest request) {
        return actions.stream()
                .filter(act -> act.canProcess(request))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no Action"));
    }

}
