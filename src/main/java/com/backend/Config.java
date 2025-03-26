package com.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configure) {
        configure.addPathPrefix("/api", handler -> true);
    }
}
