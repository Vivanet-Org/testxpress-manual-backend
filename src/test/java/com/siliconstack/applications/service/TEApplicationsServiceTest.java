package com.siliconstack.applications.service;

import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.repository.TEApplicationsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TEApplicationsServiceTest {

    @Mock
    TEApplicationsRepository teApplicationsRepository;

    @InjectMocks
    TEApplicationsService service;

    @Test
    public void return_projectlist_when_call_getAllApplications() {
        when(teApplicationsRepository.findAll()).thenReturn(Arrays.asList(new TEApplications()));
        Iterable<TEApplications> result = service.getAllTeApplications();
        assertNotNull(result);
        assertEquals(((Collection<?>) result).size(), 1);
    }

    private TEApplicationsDTO getApplicationDTO() {
        return new TEApplicationsDTO(0,"New Application", "New Application Created",
                false, 1, 0, 1234, new Date(), 0, null);
    }

    private TEApplications getApplication() {
        return new TEApplications(0,"New Application", "New Application Created",
                false, 1, 0, 1234, new Date(), 0, null);
    }

    @Test
    public void when_save_application_it_should_return_new_application() throws Exception {
        TEApplicationsDTO teApplicationsDTO = getApplicationDTO();
        TEApplications newApplications = getApplication();

        when(teApplicationsRepository.findByAppName(teApplicationsDTO.getAppName())).thenReturn(Collections.emptyList());
        when(teApplicationsRepository.save(any(TEApplications.class))).thenReturn(newApplications);
        TEApplications result = service.saveTeApplications(teApplicationsDTO);
        assertEquals(result.getAppName(), teApplicationsDTO.getAppName());
    }

    @Test
    public void return_null_when_application_already_exist_for_save_application() throws Exception {
        TEApplicationsDTO teApplicationsDTO = getApplicationDTO();

        when(teApplicationsRepository.findByAppName(teApplicationsDTO.getAppName())).thenReturn(Arrays.asList(new TEApplications()));
        TEApplications result = service.saveTeApplications(teApplicationsDTO);
        assertNull(result);
    }
}
