package com.example.kanboard_tool.services;

import com.example.kanboard_tool.domain.Project;
import com.example.kanboard_tool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        // Logic

        return projectRepository.save(project);
    }
}
