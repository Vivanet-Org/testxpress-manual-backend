package com.siliconstack.applications.controller;

import com.siliconstack.Application;
import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.service.TEApplicationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping(path = "/application")
public class ApplicationsController {

    private final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private TEApplicationsService teApplicationsService;

    private HttpHeaders headers;

    @PostConstruct
    public void initialize() {
        headers = new HttpHeaders();
        headers.add("X-Requested-With", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,x-requested-with");
    }

//    public ApplicationsController(TEApplicationsService teApplicationsService) {
//        super();
//        this.teApplicationsService = teApplicationsService;
//    }

    // build get all applications REST API
    @GetMapping(path="/getAllApplications")
    public ResponseEntity<Iterable<TEApplications>> getAllTeApplications(){
        log.info("In getAllApplications method");
        return ResponseEntity.status(HttpStatus.OK).body(teApplicationsService.getAllTeApplications());
    }

    // build create application REST API
    @PostMapping(path="/createApplication")
    public ResponseEntity<TEApplications> saveApplication(@RequestBody TEApplicationsDTO teApplicationsDTO) {
        log.info("in create project method");
        try {
            TEApplications newApplications = teApplicationsService.saveTeApplications(teApplicationsDTO);
            if (newApplications != null) {
                log.info("new Application created");
                return ResponseEntity.status(HttpStatus.CREATED).body(newApplications);
            }
            log.info("Application already exists");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).headers(headers).body(null);
        } catch (Exception e) {
            log.error("Error in createApplication: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(null);
        }
    }

    @GetMapping(path = "/searchApplications/{id}")
    public ResponseEntity<Iterable<TEApplications>> serachApplicationByProject(@PathVariable("id") int projectId) {
        List<TEApplications> applicationList = teApplicationsService.searchApplicationsByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(applicationList);
    }

    @PutMapping(path="/updateApplication/{id}")
    public ResponseEntity<TEApplications> updateApplication(@PathVariable("id") int appid,
                                                            @RequestBody TEApplicationsDTO teApplicationsDTO){
        log.info("update existing application created");
        try {
            return new ResponseEntity<>(teApplicationsService.updateApplication(teApplicationsDTO, appid), HttpStatus.OK);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
    }

    // build delete Application REST API
    // http://localhost:8090/application/deleteApplication/1
    @DeleteMapping(path="/deleteApplication/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") int appid){
        try {
            teApplicationsService.deleteApplication(appid);
            return new ResponseEntity<>("Application Deleted Successfully!.", HttpStatus.OK);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
    }
}
