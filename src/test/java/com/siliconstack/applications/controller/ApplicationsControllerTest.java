package com.siliconstack.applications.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconstack.applications.dto.TEApplicationsDTO;
import com.siliconstack.applications.model.TEApplications;
import com.siliconstack.applications.service.TEApplicationsService;
import com.siliconstack.project.service.TeProjectsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ApplicationsController.class)
public class ApplicationsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TEApplicationsService teApplicationsService;

    @MockBean
    TeProjectsService service;

    @Test
    public void testGetAllApplications_forEmptyResult() throws Exception {
        when(teApplicationsService.getAllTeApplications()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/application/getAllApplications"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateApplication_ifAlreadyExist() throws Exception {
        when(teApplicationsService.saveTeApplications(mock(TEApplicationsDTO.class))).thenReturn(null);
        mockMvc.perform( MockMvcRequestBuilders.post("/application/createApplication")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(
                        new TEApplicationsDTO(0,"New Application", "New Application Created",
                                false, 1, 0, 1234, new Date(), 0, null)

                )))
                .andExpect(status().isAlreadyReported());
    }

    @Test
    public void testCreateApplication_createApplication() throws Exception {
        TEApplications newApplication = new TEApplications(0,"New Application", "New Application Created",
                false, 1, 0, 1234, new Date(), 0, null);

        when(teApplicationsService.saveTeApplications(any())).thenReturn(newApplication);
        mockMvc.perform( MockMvcRequestBuilders.post("/application/createApplication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new TEApplicationsDTO(0,"New Application", "New Application Created",
                                false, 1, 0, 1234, new Date(), 0, null))))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
