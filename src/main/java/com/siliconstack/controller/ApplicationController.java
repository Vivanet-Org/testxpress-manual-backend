package com.siliconstack.controller;

import com.siliconstack.dto.TEApplicationDTO;
import com.siliconstack.model.TEApplication;
import com.siliconstack.service.TeApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@RestController
@EnableWebMvc
@RequestMapping(path = "/application")
public class ApplicationController {

    private final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private TeApplicationService teApplicationService;

    private HttpHeaders headers;

    @PostConstruct
    private void initialize() {
        headers = new HttpHeaders();
        headers.add("X-Requested-With", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        headers.add("Access-Control-Allow-Origin", "'*'");
        headers.add("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,x-requested-with");
    }

    @GetMapping("/getAllApplications")
    public ResponseEntity<Iterable<TEApplication>> getAllApplications() {
        log.info("In getApplication method");
        Iterable<TEApplication> listOfApplications = teApplicationService.getAllTeApplications();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(listOfApplications);
    }

    @PostMapping(path = "/addApplication", consumes = {"application/json"})
    public ResponseEntity<TEApplication> addApplication(@RequestBody TEApplicationDTO application) {
      log.info("in create application method");
      try {
    	  TEApplication newApplication = teApplicationService.saveTeApplications(application);
          if (newApplication != null) {
              log.info("new application created");
              return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(newApplication);
          }
          log.info("Application already exists");
          return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).headers(headers).body(null);
      } catch (Exception e) {
          log.error("Error in addApplication: {} " + e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(null);
      }
    }
    
    @PutMapping("/updateApplication/{id}")
    public ResponseEntity<TEApplication> updateApplication(@PathVariable("id") long id, @RequestBody TEApplicationDTO project) {
        log.info("update existing application created");
        try {
            return new ResponseEntity<TEApplication>(teApplicationService.updateApplication(project, id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
    }

    @DeleteMapping(path = "/deleteApplication/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") long appID) {
        log.info("delete application created");
        try {
            // delete application from DB
        	teApplicationService.deleteApplication(appID);
            return new ResponseEntity<String>("Application Deleted Successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Please provide a valid appid", HttpStatus.NOT_FOUND);
        }
    }
    
}
