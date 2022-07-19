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
    
    private static final String TEPROGECTS = "TeProjects";
    private static final String PROJECT_ID = "projectID";

    public TeProjectsService(TEProjectRepository teProjectsRepository) {
        this.teProjectsRepository = teProjectsRepository;
    }

    public List<TEProject> getProjectByProjectName(String projectName) {
        return teProjectsRepository.findByProjectName(projectName);
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

    public TEProject getProjectById(long projectID) {
        return teProjectsRepository.findById(projectID).orElseThrow(() ->
                new ResourceNotFoundException(TEPROGECTS, PROJECT_ID, projectID));
    }

    public TEProject updateProject(TEProjectDTO tEProjectDto, long projectID) {
      Optional<TEProject> existingEmployee = teProjectsRepository.findById(projectID);
      if (existingEmployee.isPresent()) {
          TEProject entity = existingEmployee.get();
          if (null == entity) {
              throw new ResourceNotFoundException(TEPROGECTS, PROJECT_ID, projectID);
          }
          entity.setProjectName(tEProjectDto.getProjectName());
          entity.setProjectDescription(tEProjectDto.getProjectDescription());
          entity.setUpdatedBy(tEProjectDto.getUpdatedBy());
          entity.setUpdatedOn(tEProjectDto.getUpdatedOn());
          entity.setDeleted(tEProjectDto.isDeleted());
          // save existing Project to DB
          return teProjectsRepository.save(entity);
      }
      throw new ResourceNotFoundException(TEPROGECTS, PROJECT_ID, projectID);
    }

    public void deleteProject(long projectID) {
        // check whether Project with given projectID is exist in DB or not
        Optional<TEProject> teProject = teProjectsRepository.findById(projectID);
        if (teProject.isPresent()) {
            TEProject entity = teProject.get();
            if (null == entity) {
                throw new ResourceNotFoundException(TEPROGECTS, PROJECT_ID, projectID);
            }
            teProjectsRepository.deleteById(projectID);
        }
    }
    
}
