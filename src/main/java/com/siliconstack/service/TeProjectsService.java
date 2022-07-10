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
        List<TEProject> projectList = teProjectsRepository.findByProjectName(teProjectDto.getProjectname());
        if (projectList.size() == 0) {
            TEProject entityProject = new TEProject();
            entityProject.setProjectName(teProjectDto.getProjectname());
            entityProject.setProjectDescription(teProjectDto.getProjectdescription());
            entityProject.setCreatedBy(teProjectDto.getCreatedby());
            entityProject.setCreatedOn(teProjectDto.getCreatedon());
            entityProject.setUpdatedBy(teProjectDto.getUpdatedby());
            entityProject.setUpdatedOn(teProjectDto.getUpdatedon());
            entityProject.setDeleted(teProjectDto.isIsdeleted());
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

    public TEProject updateProject(TEProjectDTO teProjectDto, int projectID) {
        Optional<TEProject> teProject = teProjectsRepository.findById(projectID);
        if (teProject.isPresent()) {
            TEProject entity = teProject.get();
            if (null == entity) {
                throw new ResourceNotFoundException("TeProjects", "projectID", projectID);
            }
            entity.setProjectName(teProjectDto.getProjectname());
            entity.setProjectDescription(teProjectDto.getProjectdescription());
            entity.setUpdatedBy(teProjectDto.getUpdatedby());
            entity.setUpdatedOn(teProjectDto.getUpdatedon());
            entity.setDeleted(teProjectDto.isIsdeleted());
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
