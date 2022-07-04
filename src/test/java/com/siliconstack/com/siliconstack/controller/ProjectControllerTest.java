package com.siliconstack.com.siliconstack.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconstack.controller.ProjectController;
import com.siliconstack.model.TEProject;
import com.siliconstack.service.TeProjectsService;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeProjectsService teProjectsService;

    @Test
    public void testGetAllProjects_forEmptyResult() throws Exception {
        when(teProjectsService.getAllTeProjects()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/project/getAllProjects"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
    }

    @Test
    public void testGetAllProjects() throws Exception {
        List<TEProject> projectList = new ArrayList<> ();
        projectList.add(new TEProject());

        when(teProjectsService.getAllTeProjects()).thenReturn(projectList);

        this.mockMvc.perform(get("/project/getAllProjects"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{'projectid':0,'projectName':null,'projectDescription':null,'createdBy':0,'createdOn':null,'updatedBy':0,'updatedOn':null,'deleted':false}]"));
    }

    @Test
    public void testAddProject_IfException() throws Exception {
        when(teProjectsService.getProjectByProjectName("Project1")).thenReturn(null);
        
        mockMvc.perform( MockMvcRequestBuilders.post("/project/addProject")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(new TEProject("Project1",null,0,null,0,null,false))))  
        .andExpect(status().isInternalServerError());
    }

    @Test
    public void testAddProject_ifAlreadyExist() throws Exception {
        List<TEProject> projectList = new ArrayList<> ();
        projectList.add(new TEProject(0,"Project1",null,0,null,0,null,false));

        when(teProjectsService.getProjectByProjectName("Project1")).thenReturn(projectList);

        mockMvc.perform( MockMvcRequestBuilders.post("/project/addProject")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(new TEProject(0,"Project1",null,0,null,0,null,false))))
        .andExpect(status().isAlreadyReported());
    }

    @Test
    public void testAddProject_createProject() throws Exception {
        List<TEProject> projectList = new ArrayList<> ();
        projectList.add(new TEProject());

        when(teProjectsService.getProjectByProjectName(anyString())).thenReturn(projectList);

        mockMvc.perform( MockMvcRequestBuilders.post("/project/addProject")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(new TEProject(null,null,0,null,0,null,false))))  
        .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateProject_successfully() throws Exception {
        TEProject existingProjects = new TEProject(0,"Project1",null,0,null,0,null,false);
        TEProject updatedProjects = new TEProject(0,"Project2","Description",0,null,1,null,false);

        when(teProjectsService.updateProject(existingProjects, 0)).thenReturn(updatedProjects);

        mockMvc.perform(MockMvcRequestBuilders.put("/project/updateProject/{id}", 0, existingProjects))
        .andExpect(status().isOk());
    }

    /**
     * @throws Exception
     */
    @Test
    public void testDeleteProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/project/deleteProject/{id}", 0))
        .andExpect(status().isOk());
    }





    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
