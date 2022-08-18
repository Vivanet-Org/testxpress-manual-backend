package com.siliconstack.applications.repository;

import com.siliconstack.applications.model.TEApplications;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TEApplicationsRepository extends CrudRepository<TEApplications, Integer> {

    public List<TEApplications> findByAppName(String appName);

    @Query(value = "SELECT * FROM te_applications WHERE projectid = :projectId", nativeQuery = true)
    public List<TEApplications> searchApplicationsByProjectId(int projectId);
}
