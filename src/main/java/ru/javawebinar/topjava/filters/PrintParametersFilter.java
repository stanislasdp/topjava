package ru.javawebinar.topjava.filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class PrintParametersFilter extends BaseFilter {

    public PrintParametersFilter() {
        System.out.println("Print parameter filter init");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        Collections
            .list(filterConfig.getInitParameterNames())
            .forEach(System.out::println);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.getParameterMap().forEach((key, value) -> System.out.println("key " + key + " value "  + value[0]));
        chain.doFilter(request, response);
    }
}
