package com.siliconstack.service;

import com.siliconstack.dto.TEProjectDTO;
import com.siliconstack.exception.ResourceNotFoundException;
import com.siliconstack.model.TEProject;
import com.siliconstack.repository.TEProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeProjectsService {

    @Autowired
    private final TEProjectRepository teProjectsRepository;

    public TeProjectsService(TEProjectRepository teProjectsRepository) {
        this.teProjectsRepository = teProjectsRepository;
    }

    public List<TEProject> getProjectByProjectName(String projectName) {
        return teProjectsRepository.findByProjectName(projectName);
    }

    public TEProject saveTeProjects(TEProjectDTO teProjectDto) throws Exception {
        List<TEProject> projectList = teProjectsRepository.findByProjectName(teProjectDto.getProjectName());
        if (projectList.size() == 0) {
            TEProject entityProject = new TEProject();
            entityProject.setProjectName(teProjectDto.getProjectName());
            entityProject.setProjectDescription(teProjectDto.getProjectDescription());
            entityProject.setCreatedBy(teProjectDto.getCreatedBy());
            entityProject.setCreatedOn(teProjectDto.getCreatedOn());
            entityProject.setUpdatedBy(teProjectDto.getUpdatedBy());
            entityProject.setUpdatedOn(teProjectDto.getUpdatedOn());
            entityProject.setDeleted(teProjectDto.isDeleted());
            return teProjectsRepository.save(entityProject);
        }
        return null;
    }

    public Iterable<TEProject> getAllTeProjects() {
        return teProjectsRepository.findAll();
    }

    public TEProject getProjectById(int projectID) {
        return teProjectsRepository.findById(projectID).orElseThrow(() ->
                new ResourceNotFoundException("TeProjects", "projectID", projectID));
    }

    public TEProject updateProject(TEProjectDTO teProjects, int projectID) {
        Optional<TEProject> teProject = teProjectsRepository.findById(projectID);
        if (teProject.isPresent()) {
            TEProject entity = teProject.get();
            if (null == entity) {
                throw new ResourceNotFoundException("TeProjects", "projectID", projectID);
            }

            entity.setProjectName(teProjects.getProjectName());
            entity.setProjectDescription(teProjects.getProjectDescription());
            entity.setDeleted(teProjects.isDeleted());
            entity.setUpdatedBy(teProjects.getUpdatedBy());
            entity.setUpdatedOn(teProjects.getUpdatedOn());
            // save existing Project to DB
            return teProjectsRepository.save(entity);
        }
        throw new ResourceNotFoundException("TeProjects", "projectID", projectID);
    }

    public void deleteProject(int projectID) {
        // check whether Project with given projectID is exist in DB or not
        Optional<TEProject> teProject = teProjectsRepository.findById(projectID);
        if (teProject.isPresent()) {
            TEProject entity = teProject.get();
            if (null == entity) {
                throw new ResourceNotFoundException("TeProjects", "projectID", projectID);
            }
            teProjectsRepository.deleteById(projectID);
        }
    }
}
