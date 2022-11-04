package com.example.kanboard_tool.services;

import com.example.kanboard_tool.domain.Project;
import com.example.kanboard_tool.exceptions.ProjectIdException;
import com.example.kanboard_tool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() +"' already exists");
        }
    }
    
    public Project findProjectByIdentifier(String projectId) {

        Project project = projectRepository.findProjectByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId +"' does not exist");

        }

        return project;
    }
}
