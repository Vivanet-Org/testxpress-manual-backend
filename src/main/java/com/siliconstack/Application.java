package com.siliconstack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.siliconstack.applications.controller.ApplicationsController;
import com.siliconstack.project.controller.ProjectController;

@SpringBootApplication
@Import({ ProjectController.class, ApplicationsController.class })
public class Application extends SpringBootServletInitializer {

    // silence console logging
    @Value("${logging.level.root:OFF}")
    String message = "";

    @Bean
    public HandlerMapping handlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public HandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer () {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("https://d1vdzah42p7xg2.cloudfront.net").allowedHeaders("*").allowedMethods("*");
            }
        };
    }

    

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}