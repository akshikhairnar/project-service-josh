package com.projectsservice.project.mapper;

import com.projectsservice.project.dto.ProjectDTO;
import com.projectsservice.project.entity.Project;

public class ProjectMapper {

    private ProjectMapper() {
    }

    public static Project mapToProject(ProjectDTO projectdto) {
        Project project = new Project();
        project.setProjectId(projectdto.getProjectId());
        project.setProjectName(projectdto.getProjectName());
        project.setProjectAllocationDate(projectdto.getProjectAllocationDate());
       // project.setEmployeeId(projectdto.getEmployeeId());
        return project;
    }

    public static ProjectDTO mapToProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setProjectAllocationDate(project.getProjectAllocationDate());
       // projectDTO.setEmployeeId(project.getEmployeeId());
        return projectDTO;
    }
}
