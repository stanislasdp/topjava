package ru.javawebinar.topjava.web.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.javawebinar.topjava.web.meal.action.Action;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class MealServlet extends HttpServlet {

    @Autowired
    private List<Action> actions;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
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
