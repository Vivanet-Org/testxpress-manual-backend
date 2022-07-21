package com.siliconstack.project.service;

import com.siliconstack.project.dto.TEProjectDTO;
import com.siliconstack.project.exception.ResourceNotFoundException;
import com.siliconstack.project.model.TEProject;
import com.siliconstack.project.repository.TEProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeProjectsService {

    private static final String TEPROGECTS = "TeProjects";
    private static final String PROJECT_ID = "projectID";
    @Autowired
    private final TEProjectRepository teProjectsRepository;

    public TeProjectsService(TEProjectRepository teProjectsRepository) {
        this.teProjectsRepository = teProjectsRepository;
    }

    public TEProject saveTeProjects(TEProjectDTO teProjectDto) throws Exception {
        List<TEProject> projectList = teProjectsRepository.findByProjectName(teProjectDto.getProjectName());
        if (projectList.isEmpty()) {
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

    public TEProject updateProject(TEProjectDTO teProjectDto, int projectID) {
        Optional<TEProject> teProject = teProjectsRepository.findById(projectID);
        if (teProject.isPresent()) {
            TEProject entity = teProject.get();
            if (null == entity) {
                throw new ResourceNotFoundException(TEPROGECTS, PROJECT_ID, projectID);
            }
            entity.setProjectName(teProjectDto.getProjectName());
            entity.setProjectDescription(teProjectDto.getProjectDescription());
            entity.setUpdatedBy(teProjectDto.getUpdatedBy());
            entity.setUpdatedOn(teProjectDto.getUpdatedOn());
            entity.setDeleted(teProjectDto.isDeleted());
            // save existing Project to DB
            return teProjectsRepository.save(entity);
        }
        throw new ResourceNotFoundException(TEPROGECTS, PROJECT_ID, projectID);
    }

    public void deleteProject(int projectID) {
        // check whether Project with given projectID is exist in DB or not
        Optional<TEProject> teProject = teProjectsRepository.findById(projectID);
        if (teProject.isPresent()) {
            teProjectsRepository.deleteById(projectID);
        }
    }
}