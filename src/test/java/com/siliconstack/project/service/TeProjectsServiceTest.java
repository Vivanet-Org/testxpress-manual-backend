package com.siliconstack.project.service;

import com.siliconstack.project.dto.TEProjectDTO;
import com.siliconstack.project.exception.ResourceNotFoundException;
import com.siliconstack.project.model.TEProject;
import com.siliconstack.project.repository.TEProjectRepository;
import com.siliconstack.project.service.TeProjectsService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeProjectsServiceTest {

    @Mock
    TEProjectRepository teProjectsRepository;

    @InjectMocks
    TeProjectsService service;

    @Test
    public void when_save_project_it_should_return_new_project() throws Exception {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project1","Test Project",1234,new Date(),0,null,false);
        TEProject newProject = new TEProject(0,"Project1","Test Project",false, 1234,new Date(),0,null);

        when(teProjectsRepository.findByProjectName(teProjectDto.getProjectName())).thenReturn(Collections.emptyList());
        when(teProjectsRepository.save(any(TEProject.class))).thenReturn(newProject);
        TEProject result = service.saveTeProjects(teProjectDto);
        assertEquals(result.getProjectName(), teProjectDto.getProjectName());
    }

    @Test
    public void return_null_when_project_already_exist_for_save_project() throws Exception {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project1","Test Project",1234,new Date(),0,null,false);

        List<TEProject> projectList = new ArrayList<>();
        projectList.add(new TEProject());

        when(teProjectsRepository.findByProjectName(anyString())).thenReturn(projectList);
        TEProject result = service.saveTeProjects(teProjectDto);
        assertNull(result);
    }

    @Test
    public void return_projectlist_when_call_getAllProject() {
        TEProject teProjectDto = new TEProject(0,"Project1","Test Project",false, 1234, new Date(), 0, null);

        when(teProjectsRepository.findAll()).thenReturn(Arrays.asList(teProjectDto));
        Iterable<TEProject> result = service.getAllTeProjects();
        assertNotNull(result);
        assertEquals(((Collection<?>) result).size(), 1);
    }

    @Test
    public void update_project_when_there_is_change() {
        TEProjectDTO teProjectDto = new TEProjectDTO(0,"Project12","Test Project",1234,new Date(),1234,new Date(),false);
        int projectID = 0;
        Optional<TEProject> teProject = Optional.of(new TEProject(0, "Project1", "Test Project", false, 1234, new Date(), 0, null));
        TEProject newProject = new TEProject(0,"Project12","Test Project",false, 1234,new Date(),1234,new Date());

        when(teProjectsRepository.findById(projectID)).thenReturn(teProject);

        when(teProjectsRepository.save(any(TEProject.class))).thenReturn(newProject);

        TEProject result = service.updateProject(teProjectDto, projectID);
        assertEquals(result.getProjectName(), teProjectDto.getProjectName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void throw_exception_if_project_not_found_during_update() {
        when(teProjectsRepository.findById(0)).thenReturn( Optional.empty());

        TEProject result = service.updateProject(new TEProjectDTO(), 0);
    }

    @DisplayName("Test Delete function")
    @Test
    public void delete_project_if_available() {
        int projectID = 0;
        Optional<TEProject> teProject = Optional.of(new TEProject(0, "Project1", "Test Project", false, 1234, new Date(), 0, null));
        when(teProjectsRepository.findById(projectID)).thenReturn(teProject);
        service.deleteProject(projectID);

    }
}
