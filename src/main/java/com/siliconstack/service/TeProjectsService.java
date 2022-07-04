package com.siliconstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siliconstack.exception.ResourceNotFoundException;
import com.siliconstack.model.TEProject;
import com.siliconstack.repository.TEProjectRepository;

@Service
public class TeProjectsService {
    
	@Autowired
    private TEProjectRepository teProjectsRepository;

	public TeProjectsService(TEProjectRepository teProjectsRepository) {
		this.teProjectsRepository = teProjectsRepository;
	}

	public List<TEProject> getProjectByProjectName(String projectName) {
		return teProjectsRepository.findByProjectName(projectName);
	}
	
	public TEProject saveTeProjects(TEProject teProjects) {
		return teProjectsRepository.save(teProjects);
	}

	public Iterable<TEProject> getAllTeProjects() {
		return teProjectsRepository.findAll();
	}

	public TEProject getProjectById(int projectID) {
		return teProjectsRepository.findById(projectID).orElseThrow(() -> 
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
	}
	
	public TEProject updateProject(TEProject teProjects, int projectID) {
		// we need to check whether employee with given projectID is exist in DB or not
		TEProject existingProject = teProjectsRepository.findById(projectID).orElseThrow(() ->
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		existingProject.setProjectName(teProjects.getProjectName());
		existingProject.setProjectDescription(teProjects.getProjectDescription());
		existingProject.setDeleted(teProjects.isDeleted());
		existingProject.setUpdatedBy(teProjects.getUpdatedBy());
		existingProject.setUpdatedOn(teProjects.getUpdatedOn());
		// save existing Project to DB
		teProjectsRepository.save(existingProject);
		return existingProject;
	}

	public void deleteProject(int projectID) {
		// check whether Project with given projectID is exist in DB or not 
		teProjectsRepository.findById(projectID).orElseThrow(() -> 
				new ResourceNotFoundException("TeProjects", "projectID", projectID));
		teProjectsRepository.deleteById(projectID);

	}
}
