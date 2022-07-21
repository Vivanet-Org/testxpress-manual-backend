package com.siliconstack.application.service;

import com.siliconstack.application.repository.TEApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TEApplicationService {

    @Autowired
    private TEApplicationRepository repository;

    public TEApplicationService(TEApplicationRepository repository) {
        this.repository = repository;
    }
}
