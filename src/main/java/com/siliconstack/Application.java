package com.siliconstack;

import com.siliconstack.project.controller.ProjectController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "com.siliconstack.controller")
@Import({ProjectController.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*
     * Create required HandlerMapping, to avoid several default HandlerMapping
     * instances being created
     */
    @Bean
    public HandlerMapping handlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    /*
     * Create required HandlerAdapter, to avoid several default HandlerAdapter
     * instances being created
     */
    @Bean
    public HandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //registry.addMapping("/**").allowedOrigins("*.cloudfront.net").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
            }
        };
    }
}