package ru.javawebinar.topjava.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =
        {"ru.javawebinar.topjava.converter",
                "ru.javawebinar.topjava.mapper",
                "ru.javawebinar.topjava.repository",
                "ru.javawebinar.topjava.service",
                "ru.javawebinar.topjava.web"})
public class ApplicationConfiguration {


}


