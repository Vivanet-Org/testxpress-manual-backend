package com.siliconstack.project.controller;

import javax.annotation.PostConstruct;

import com.siliconstack.project.model.TEProjects;
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

import com.siliconstack.Application;
import com.siliconstack.project.dto.TEProjectDTO;
import com.siliconstack.project.service.TeProjectsService;

import java.util.List;
import java.util.Map;

@RestController
@EnableWebMvc
@RequestMapping(path = "/project")
public class ProjectController {

    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TeProjectsService teProjectsService;

    private HttpHeaders headers;

	@PostConstruct
	private void initialize() {
		headers = new HttpHeaders();
		headers.add("X-Requested-With", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
		headers.add("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,x-requested-with");
	}

    @GetMapping("/getAllProjects")
    public ResponseEntity<Iterable<TEProjects>> getAllProjects() {
        log.info("In getProject method");
        Iterable<TEProjects> listOfProject = teProjectsService.getAllTeProjects();
        return ResponseEntity.status(HttpStatus.OK).body(listOfProject);
    }

    @PostMapping(path = "/addProject", consumes = {"application/json"})
    public ResponseEntity<TEProjects> addProject(@RequestBody TEProjectDTO project) {
        log.info("in create project method");
        try {
            TEProjects newProject = teProjectsService.saveTeProjects(project);
            if (newProject != null) {
                log.info("new project created");
                return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
            }
            log.info("Project already exists");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).headers(headers).body(null);
        } catch (Exception e) {
            log.error("Error in addProject: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(null);
        }
    }

    @PutMapping("/updateProject/{id}")
    public ResponseEntity<TEProjects> updateProject(@PathVariable("id") int id, @RequestBody TEProjectDTO project) {
        log.info("update existing project created");
        try {
            return new ResponseEntity<>(teProjectsService.updateProject(project, id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
    }

    @DeleteMapping(path = "/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") int projectID) {
        log.info("delete project created");
        try {
            // delete employee from DB
            teProjectsService.deleteProject(projectID);
            return new ResponseEntity<>("Project Deleted Successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Please provide a valid projectID", HttpStatus.NOT_FOUND);
		}
	}

    // build get projectId projectName REST API
    @GetMapping(path="/getProjectIdProjectName")
    public ResponseEntity<List<Map<Integer, String>>> getProjectIdProjectName(){
        return ResponseEntity.status(HttpStatus.OK).body(teProjectsService.getProjectIdAndProjectName());
    }

    @GetMapping(path="/searchProjects/{searchStr}")
    public ResponseEntity<List<TEProjects>> searchProjects(@PathVariable("searchStr") String searchString) {
        return ResponseEntity.status(HttpStatus.OK).body(teProjectsService.searchProjectByNameAndDescription(searchString));
    }
}