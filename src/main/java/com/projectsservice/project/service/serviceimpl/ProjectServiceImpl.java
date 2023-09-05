package com.projectsservice.project.service.serviceimpl;

import com.projectsservice.project.entity.Project;
import com.projectsservice.project.exceptions.projectexceptions.NoSuchProjectExistsException;
import com.projectsservice.project.exceptions.projectexceptions.ProjectAlreadyExistException;
import com.projectsservice.project.repository.ProjectRepository;
import com.projectsservice.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project addProject(Project project) {
        Project existingProject = projectRepository.findByProjectName(project.getProjectName());
        if (existingProject == null) {
            return projectRepository.save(project);
        } else {
            throw new ProjectAlreadyExistException("Project Already Exist");
        }


    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Project project, Long id) {
        Project projectToUpdate = findByProjectId(id);
        projectToUpdate.setProjectName(project.getProjectName());
        projectToUpdate.setProjectAllocationDate(project.getProjectAllocationDate());
       // projectToUpdate.setEmployeeId(project.getEmployeeId());
        return projectRepository.save(project);
    }

    @Override
    public Project findByProjectId(Long id) {
        return projectRepository.findByProjectId(id);
                //.orElseThrow(() -> new NoSuchProjectExistsException("Project Not Exist With ID " + id));
    }

    @Override
    public void deleteProjectById(Long id) {
        Project projectToDelete = findByProjectId(id);
        if (projectToDelete != null) {
            projectRepository.deleteById(id);
        }
    }


}
