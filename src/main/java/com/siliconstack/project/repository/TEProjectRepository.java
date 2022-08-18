package com.siliconstack.project.repository;

import com.siliconstack.project.model.TEProjects;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TEProjectRepository extends CrudRepository<TEProjects, Integer> {

    List<TEProjects> findByProjectName(String projectName);

    @Query(value = "SELECT projectid, projectname FROM te_projects WHERE isdeleted = false ORDER BY projectname ASC", nativeQuery = true)
    public List<Map<Integer, String>> getProjectIdAndName();

    @Query(value = "SELECT *" + " FROM te_projects WHERE (projectname LIKE %:searchString% OR projectdescription LIKE %:searchString%) ", nativeQuery = true)
    public List<TEProjects> searchProjectByNameAndDescription(String searchString);
}
