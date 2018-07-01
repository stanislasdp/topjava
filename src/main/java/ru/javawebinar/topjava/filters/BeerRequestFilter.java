package ru.javawebinar.topjava.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class BeerRequestFilter extends BaseFilter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = request.getRemoteUser();

        if (Objects.nonNull(name)) {
            filterConfig.getServletContext().log(String.format("User %s is updating", name));
        }
        chain.doFilter(request, response);
    }
}
