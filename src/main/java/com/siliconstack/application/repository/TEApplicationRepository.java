package com.siliconstack.application.repository;

import com.siliconstack.application.model.TEApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TEApplicationRepository extends CrudRepository<TEApplication, Integer> {
}
