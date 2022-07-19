package com.siliconstack.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siliconstack.model.TEApplication;

@Repository
public interface TEApplicationRepository extends CrudRepository <TEApplication, Long>{
    
    public List<TEApplication> findByAppName(String appName);

}
