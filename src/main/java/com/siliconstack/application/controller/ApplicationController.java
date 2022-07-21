package com.siliconstack.application.controller;

import com.siliconstack.Application;
import com.siliconstack.application.service.TEApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@RestController
@EnableWebMvc
@RequestMapping("/application")
public class ApplicationController {

    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TEApplicationService service;

    private HttpHeaders headers;

    @PostConstruct
    private void initialize() {
        setHeaders();
    }

    private void setHeaders() {
        headers = new HttpHeaders();
        headers.add("X-Requested-With", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,x-requested-with");
    }
}
