package com.siliconstack.controller;

import com.siliconstack.dto.TEProjectDTO;
import com.siliconstack.model.TEProject;
import com.siliconstack.service.TeProjectsService;
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
@RequestMapping(path = "/project")
public class ProjectController {

    private final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private TeProjectsService teProjectsService;

    private HttpHeaders headers;

    @PostConstruct
    private void initialize() {
        headers = new HttpHeaders();
        headers.add("X-Requested-With", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        headers.add("Access-Control-Allow-Origin", "'*'");
        headers.add("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,x-requested-with");
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<Iterable<TEProject>> getAllProjects() {
        log.info("In getProject method");
        Iterable<TEProject> listOfProject = teProjectsService.getAllTeProjects();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(listOfProject);
    }

    @PostMapping(path = "/addProject", consumes = {"application/json"})
    public ResponseEntity<TEProject> addProject(@RequestBody TEProjectDTO project) {
      log.info("in create project method");
      try {
          TEProject newProject = teProjectsService.saveTeProjects(project);
          if (newProject != null) {
              log.info("new project created");
              return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(newProject);
          }
          log.info("Project already exists");
          return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).headers(headers).body(null);
      } catch (Exception e) {
          log.error("Error in addProject: {} " + e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(null);
      }
    }
    
    @PutMapping("/updateProject/{id}")
    public ResponseEntity<TEProject> updateProject(@PathVariable("id") long id, @RequestBody TEProjectDTO project) {
        log.info("update existing project created");
        try {
            return new ResponseEntity<TEProject>(teProjectsService.updateProject(project, id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
    }

    @DeleteMapping(path = "/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") long projectID) {
        log.info("delete project created");
        try {
            // delete Project from DB
            teProjectsService.deleteProject(projectID);
            return new ResponseEntity<String>("Project Deleted Successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Please provide a valid projectID", HttpStatus.NOT_FOUND);
        }
    }
    
}
