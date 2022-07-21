package com.siliconstack.project.repository;

import com.siliconstack.project.model.TEProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TEProjectRepository extends CrudRepository<TEProject, Integer> {

    List<TEProject> findByProjectName(String projectName);

}
