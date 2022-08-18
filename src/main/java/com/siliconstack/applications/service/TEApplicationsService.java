package com.siliconstack.applications.service;

import java.util.List;
import java.util.Optional;

import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.project.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.repository.TEApplicationsRepository;

@Service
public class TEApplicationsService {

    private TEApplicationsRepository teApplicationsRepository;

    public TEApplicationsService(TEApplicationsRepository teApplicationsRepository) {
        this.teApplicationsRepository = teApplicationsRepository;
    }

    public Iterable<TEApplications> getAllTeApplications() {
        return teApplicationsRepository.findAll();
    }

    public TEApplications saveTeApplications(TEApplicationsDTO teApplicationsDTO) {
        List<TEApplications> applicationList = teApplicationsRepository.findByAppName(teApplicationsDTO.getAppName());
        if(applicationList.isEmpty()) {
            TEApplications teApplications = new TEApplications();
            teApplications.setAppDescription(teApplicationsDTO.getAppDescription());
            teApplications.setAppName(teApplicationsDTO.getAppName());
            teApplications.setDeleted(teApplicationsDTO.isDeleted());
            teApplications.setPlatformID(teApplicationsDTO.getPlatformID());
            teApplications.setProjectID(teApplicationsDTO.getProjectID());
            teApplications.setCreatedBy(teApplicationsDTO.getCreatedBy());
            teApplications.setCreatedOn(teApplicationsDTO.getCreatedOn());
            teApplications.setUpdatedBy(teApplicationsDTO.getUpdatedBy());
            teApplications.setUpdatedOn(teApplicationsDTO.getUpdatedOn());
            return teApplicationsRepository.save(teApplications);
        }
        return null;
    }

    public List<TEApplications> searchApplicationsByProjectId(int projectId) {
        return teApplicationsRepository.searchApplicationsByProjectId(projectId);
    }

    public TEApplications updateApplication(TEApplicationsDTO teApplicationsDTO, int appId) {
        Optional<TEApplications> teApplications = teApplicationsRepository.findById(appId);
        if (teApplications.isPresent()) {
            TEApplications entity = teApplications.get();
            if (null == entity) {
                throw new ResourceNotFoundException("TEApplications", "appId", appId);
            }
            entity.setAppName(teApplicationsDTO.getAppName());
            entity.setAppDescription(teApplicationsDTO.getAppDescription());
            entity.setDeleted(teApplicationsDTO.isDeleted());
            entity.setProjectID(teApplicationsDTO.getProjectID());
            entity.setUpdatedBy(teApplicationsDTO.getUpdatedBy());
            entity.setUpdatedOn(teApplicationsDTO.getUpdatedOn());
            return teApplicationsRepository.save(entity);
        }
        throw new ResourceNotFoundException("TEApplications", "appId", appId);
    }

    public void deleteApplication(int appId) {
        Optional<TEApplications> teApplications = teApplicationsRepository.findById(appId);
        if (teApplications.isPresent()) {
            teApplicationsRepository.deleteById((int) appId);
        }
    }
}
