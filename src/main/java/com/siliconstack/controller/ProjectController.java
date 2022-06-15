package com.siliconstack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siliconstack.model.TEProject;
import com.siliconstack.repository.TEProjectRepository;

import java.util.List;

@RestController
public class ProjectController {
    
    private Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    TEProjectRepository projectRepository;

    @GetMapping("/getAllProjects")
    public Iterable<TEProject> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("/addProject")
    public TEProject addProject(TEProject project) {
        try {
            List<TEProject> projectList = projectRepository.findByProjectName(project.getProjectName());
            if(projectList.size() == 0 ) {
                return projectRepository.save(project);
            }
            log.info("Project already exists");
            return null;
        } catch (Exception e) {
            log.error("Error in addProject: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/updateProject/{id}")
    public TEProject updateProject(@PathVariable("id") int id, TEProject project) {
        try {
            TEProject projectToUpdate = projectRepository.findById(id).get();
            projectToUpdate.setProjectName(project.getProjectName());
            projectToUpdate.setProjectDescription(project.getProjectDescription());
            projectToUpdate.setDeleted(project.isDeleted());
            projectToUpdate.setCreatedBy(project.getCreatedBy());
            projectToUpdate.setCreatedOn(project.getCreatedOn());
            projectToUpdate.setUpdatedBy(project.getUpdatedBy());
            projectToUpdate.setUpdatedOn(project.getUpdatedOn());
            projectRepository.save(projectToUpdate);
            return projectToUpdate;
        } catch (Exception e) {
            log.error("Error in updateProject: " + e.getMessage());
            return null;
        }
    }         
}
