package com.projectsservice.project.controller;

import com.projectsservice.project.daprservice.DaprService;
import com.projectsservice.project.dto.ProjectDTO;
import com.projectsservice.project.entity.Project;
import com.projectsservice.project.mapper.ProjectMapper;
import com.projectsservice.project.service.ProjectService;
import io.dapr.Topic;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.CloudEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private DaprService daprService;

    @Autowired
    private ProjectService projectService;

    @Topic(name = "testdapr", pubsubName = "employeepubsub")
    @PostMapping(value = "/notify",consumes = MediaType.ALL_VALUE)
    public Mono<Void> getNotification(@RequestBody (required = false) CloudEvent<String> cloudEvent){
        log.info("Project get notification method call");
        return daprService.getNotification(cloudEvent);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDTO) {
        Project projectToAdd = ProjectMapper.mapToProject(projectDTO);
        Project project = projectService.addProject(projectToAdd);
        ProjectDTO projectDTOAdded = ProjectMapper.mapToProjectDTO(project);
        return new ResponseEntity<>(projectDTOAdded, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") Long id) {
        Project project = projectService.findByProjectId(id);
        if(project==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        ProjectDTO projectDTO = ProjectMapper.mapToProjectDTO(project);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable("id") Long id) {
        Project projectToUpdate = ProjectMapper.mapToProject(projectDTO);
        Project updatedProject = projectService.updateProject(projectToUpdate, id);
        ProjectDTO updatedProjectDTO = ProjectMapper.mapToProjectDTO(updatedProject);
        return new ResponseEntity<>(updatedProjectDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
