package com.siliconstack.repository;

import java.util.List;

import com.siliconstack.model.TEProject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TEProjectRepository extends CrudRepository <TEProject, Long>{
    
    public List<TEProject> findByProjectName(String projectName);

}
