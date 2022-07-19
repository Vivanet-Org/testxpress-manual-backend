package com.siliconstack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siliconstack.dto.TEApplicationDTO;
import com.siliconstack.exception.ResourceNotFoundException;
import com.siliconstack.model.TEApplication;
import com.siliconstack.repository.TEApplicationRepository;

@Service
public class TeApplicationService {
	
	@Autowired
    private final TEApplicationRepository teApplicationRepository;
    
    private static final String TEAPPLICATIONS = "TeApplications";
    private static final String APP_ID = "appID";

    public TeApplicationService(TEApplicationRepository teApplicationRepository) {
        this.teApplicationRepository = teApplicationRepository;
    }

    public List<TEApplication> getApplicationByAppName(String appName) {
        return teApplicationRepository.findByAppName(appName);
    }

    public TEApplication saveTeApplications(TEApplicationDTO teApplicationDTO) throws Exception {
      List<TEApplication> applicationList = teApplicationRepository.findByAppName(teApplicationDTO.getAppName());
      if (applicationList.isEmpty()) {
    	  TEApplication entityApplication = new TEApplication();
    	  entityApplication.setAppName(teApplicationDTO.getAppName());
    	  entityApplication.setAppDescription(teApplicationDTO.getAppDescription());
    	  entityApplication.setProjectID(teApplicationDTO.getProjectID());
    	  entityApplication.setCreatedBy(teApplicationDTO.getCreatedBy());
    	  entityApplication.setPlatformID(teApplicationDTO.getPlatformID());
    	  entityApplication.setCreatedOn(teApplicationDTO.getCreatedOn());
    	  entityApplication.setUpdatedBy(teApplicationDTO.getUpdatedBy());
    	  entityApplication.setUpdatedOn(teApplicationDTO.getUpdatedOn());
    	  entityApplication.setDeleted(teApplicationDTO.isDeleted());
          return teApplicationRepository.save(entityApplication);
      }
      return null;
    }

    public Iterable<TEApplication> getAllTeApplications() {
        return teApplicationRepository.findAll();
    }

    public TEApplication getApplicationById(long appID) {
        return teApplicationRepository.findById(appID).orElseThrow(() ->
                new ResourceNotFoundException(TEAPPLICATIONS, APP_ID, appID));
    }

    public TEApplication updateApplication(TEApplicationDTO tEApplicationDTO, long appID) {
      Optional<TEApplication> existingApplication = teApplicationRepository.findById(appID);
      if (existingApplication.isPresent()) {
    	  TEApplication entity = existingApplication.get();
          if (null == entity) {
              throw new ResourceNotFoundException(TEAPPLICATIONS, APP_ID, appID);
          }
          entity.setAppName(tEApplicationDTO.getAppName());
          entity.setAppDescription(tEApplicationDTO.getAppDescription());
          entity.setUpdatedBy(tEApplicationDTO.getUpdatedBy());
          entity.setUpdatedOn(tEApplicationDTO.getUpdatedOn());
          entity.setDeleted(tEApplicationDTO.isDeleted());
          // save existing Application to DB
          return teApplicationRepository.save(entity);
      }
      throw new ResourceNotFoundException(TEAPPLICATIONS, APP_ID, appID);
    }

    public void deleteApplication(long appID) {
        // check whether Application with given appid is exist in DB or not
        Optional<TEApplication> teApplication = teApplicationRepository.findById(appID);
        if (teApplication.isPresent()) {
        	TEApplication entity = teApplication.get();
            if (null == entity) {
                throw new ResourceNotFoundException(TEAPPLICATIONS, APP_ID, appID);
            }
            teApplicationRepository.deleteById(appID);
        }
    }

}
