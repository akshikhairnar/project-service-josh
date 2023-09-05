package com.projectsservice.project.service;

import com.projectsservice.project.entity.Project;

import java.util.List;

public interface ProjectService {
    Project addProject(Project project);

    List<Project> getAllProjects();

    Project updateProject(Project project, Long id);

    Project findByProjectId(Long id);

    void deleteProjectById(Long id);


}
