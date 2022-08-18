package com.siliconstack.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.siliconstack.project.model.TEProjects;
import com.siliconstack.project.dto.TEProjectDTO;
import com.siliconstack.project.exception.ResourceNotFoundException;
import com.siliconstack.project.repository.TEProjectRepository;

@RunWith(MockitoJUnitRunner.class)
public class TeProjectsServiceTest {

    @Mock
    TEProjectRepository teProjectsRepository;

    @InjectMocks
    TeProjectsService service;

    @Test
    public void when_save_project_it_should_return_new_project() throws Exception {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project1","Test Project",1234,new Date(),0,null,false);
        TEProjects newProject = new TEProjects(0,"Project1","Test Project",false, 1234,new Date(),0,null);

        when(teProjectsRepository.findByProjectName(teProjectDto.getProjectName())).thenReturn(Collections.emptyList());
        when(teProjectsRepository.save(any(TEProjects.class))).thenReturn(newProject);
        TEProjects result = service.saveTeProjects(teProjectDto);
        assertEquals(result.getProjectName(), teProjectDto.getProjectName());
    }

    @Test
    public void return_null_when_project_already_exist_for_save_project() throws Exception {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project1","Test Project",1234,new Date(),0,null,false);

        List<TEProjects> projectList = new ArrayList<>();
        projectList.add(new TEProjects());

        when(teProjectsRepository.findByProjectName(anyString())).thenReturn(projectList);
        TEProjects result = service.saveTeProjects(teProjectDto);
        assertNull(result);
    }

    @Test
    public void return_projectlist_when_call_getAllProject() {
        TEProjects teProjectDto = new TEProjects(0,"Project1","Test Project",false, 1234, new Date(), 0, null);

        when(teProjectsRepository.findAll()).thenReturn(Arrays.asList(teProjectDto));
        Iterable<TEProjects> result = service.getAllTeProjects();
        assertNotNull(result);
        assertEquals(((Collection<?>) result).size(), 1);
    }

    @Test
    public void update_project_when_there_is_change() {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project12","Test Project",1234,new Date(),1234,new Date(),false);
        int projectID = 0;
        Optional<TEProjects> teProject = Optional.of(new TEProjects(0, "Project1", "Test Project", false, 1234, new Date(), 0, null));
        TEProjects newProject = new TEProjects(0,"Project12","Test Project",false, 1234,new Date(),1234,new Date());

        when(teProjectsRepository.findById(projectID)).thenReturn(teProject);

        when(teProjectsRepository.save(any(TEProjects.class))).thenReturn(newProject);

        TEProjects result = service.updateProject(teProjectDto, projectID);
        assertEquals(result.getProjectName(), teProjectDto.getProjectName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void throw_exception_if_project_not_found_during_update() {
        when(teProjectsRepository.findById(0)).thenReturn( Optional.empty());

        TEProjects result = service.updateProject(new TEProjectDTO(), 0);
    }

    @Test
    public void delete_project_if_available() {
        int projectID = 0;
        Optional<TEProjects> teProject = Optional.of(new TEProjects(0, "Project1", "Test Project", false, 1234, new Date(), 0, null));
        when(teProjectsRepository.findById(projectID)).thenReturn(teProject);
        service.deleteProject(projectID);
    }

    @Test
    public void test_getProjectIdAndProjectName_withoutError() {
        Map<Integer, String> projectNameMap = new HashMap<>();
        projectNameMap.put(1, "TestProject1");
        projectNameMap.put(2, "TestProject2");
        projectNameMap.put(3, "TestProject3");

        List<Map<Integer, String>> projectList = Arrays.asList(projectNameMap);

        when(teProjectsRepository.getProjectIdAndName()).thenReturn(projectList);

        List<Map<Integer, String>> result = service.getProjectIdAndProjectName();

        assertEquals(result.size(), projectList.size());
    }
}
