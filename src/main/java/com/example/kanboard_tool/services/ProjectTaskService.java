package com.example.kanboard_tool.services;

import com.example.kanboard_tool.domain.Backlog;
import com.example.kanboard_tool.domain.ProjectTask;
import com.example.kanboard_tool.repositories.BacklogRepository;
import com.example.kanboard_tool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        // Exceptions: Project not found

        // PTs to be added to a specific project, project != null, BL exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        // set the BL to PT
        projectTask.setBacklog(backlog);
        // we want our project sequence to bee like this: IDPRO-1 IDPRO-2 ... IDPRO-100 IDPRO-101
        Integer BacklogSequence = backlog.getPTSequence();
        // Update the BL sequence
        BacklogSequence++;

        backlog.setPTSequence(BacklogSequence);

        // Add Sequence to PT
        projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        // INITIAL priority when priority is null
        if(projectTask.getPriority() == null){ // In the future we need projectTask.getPriority() == 0 to handle to form
            projectTask.setPriority(3);
        }
        // INITIAL status when status is null
        if(projectTask.getStatus() == "" || projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }
}
