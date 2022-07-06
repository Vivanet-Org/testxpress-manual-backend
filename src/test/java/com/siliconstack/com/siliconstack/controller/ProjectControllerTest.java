package com.siliconstack.com.siliconstack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconstack.controller.ProjectController;
import com.siliconstack.dto.TEProjectDTO;
import com.siliconstack.model.TEProject;
import com.siliconstack.service.TeProjectsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeProjectsService teProjectsService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllProjects_forEmptyResult() throws Exception {
        when(teProjectsService.getAllTeProjects()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/project/getAllProjects"))
                .andExpect(status().isOk());

    }
     @Test
     public void testGetAllProjects() throws Exception {
         List<TEProject> projectList = new ArrayList<>();
         projectList.add(new TEProject());

         when(teProjectsService.getAllTeProjects()).thenReturn(projectList);

         this.mockMvc.perform(get("/project/getAllProjects"))
             .andExpect(status().isOk())
             .andExpect(content().json("[{'projectid':0,'projectName':null,'projectDescription':null,'createdBy':0,'createdOn':null,'updatedBy':0,'updatedOn':null,'deleted':false}]"));
     }

     @Test
     public void testAddProject_ifAlreadyExist() throws Exception {
         when(teProjectsService.saveTeProjects(mock(TEProjectDTO.class))).thenReturn(null);

         mockMvc.perform( MockMvcRequestBuilders.post("/project/addProject")
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(new TEProjectDTO("Project1",null,0,null,0,null,false))))
         .andExpect(status().isAlreadyReported());
     }

     @Test
     public void testAddProject_ifProjectObjectIsNull() throws Exception {
         TEProjectDTO project = new TEProjectDTO();
         TEProject newProject = new TEProject(0,"Project1",null,0,null,0,null,false);

         when(teProjectsService.saveTeProjects(new TEProjectDTO())).thenThrow(Exception.class);

         mockMvc.perform( MockMvcRequestBuilders.post("/project/addProject")
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(null)))
         .andExpect(status().isBadRequest());
     }

     @Test
     public void testAddProject_createProject() throws Exception {
         TEProjectDTO project = new TEProjectDTO();
         TEProject newProject = new TEProject(0,"Project1",null,0,null,0,null,false);

         when(teProjectsService.saveTeProjects(any())).thenReturn(newProject);
         mockMvc.perform( MockMvcRequestBuilders.post("/project/addProject")
         .contentType(MediaType.APPLICATION_JSON)
         .accept(MediaType.APPLICATION_JSON)
         .content(asJsonString(new TEProjectDTO("Project1",null,0,null,0,null,false))))
         .andExpect(status().isCreated());
     }

     @Test
     public void testUpdateProject_successfully() throws Exception {
         TEProjectDTO existingProjects = new TEProjectDTO("Project1",null,0,null,0,null,false);
         TEProject updatedProjects = new TEProject(0,"Project2","Description",0,null,1,null,false);

         when(teProjectsService.updateProject(existingProjects, 0)).thenReturn(updatedProjects);

         mockMvc.perform(MockMvcRequestBuilders.put("/project/updateProject/{id}", 0, existingProjects))
         .andExpect(status().isOk());
     }

     @Test
     public void testDeleteProject() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.delete("/project/deleteProject/{id}", 0))
         .andExpect(status().isOk());
     }



}
