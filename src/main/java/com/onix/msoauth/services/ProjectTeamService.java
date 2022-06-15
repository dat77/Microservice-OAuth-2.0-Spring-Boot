package com.onix.msoauth.services;

import com.onix.msoauth.entities.ProjectStatus;
import com.onix.msoauth.entities.ProjectTeam;
import com.onix.msoauth.repository.ProjectTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTeamService {
    private ProjectTeamRepository projectTeamRepository;

    @Autowired
    public ProjectTeamService(ProjectTeamRepository projectTeamRepository) {
        this.projectTeamRepository = projectTeamRepository;
    }

    public ProjectTeam create(String code, String projectName, String description, ProjectStatus status){
        return projectTeamRepository.findById(code)
                .orElse(projectTeamRepository.save(new ProjectTeam(code,projectName,description, status)));
    }

    public Iterable<ProjectTeam> review(){
        return projectTeamRepository.findAll();
    }

    public long count(){
        return projectTeamRepository.count();
    }
}
