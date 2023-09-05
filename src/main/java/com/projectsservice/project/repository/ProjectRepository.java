package com.projectsservice.project.repository;

import com.projectsservice.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByProjectName(String name);

    Project findByProjectId(Long id);
}
