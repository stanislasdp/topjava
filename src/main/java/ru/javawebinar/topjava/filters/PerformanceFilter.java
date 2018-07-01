package ru.javawebinar.topjava.filters;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PerformanceFilter extends BaseFilter {

    public PerformanceFilter() {
        System.out.println("Performance filter listener init");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        long inTime = System.nanoTime();
        chain.doFilter(request, response);
        long outTime = System.nanoTime();
        System.out.println("Processing "  + (outTime - inTime));
    }
}
